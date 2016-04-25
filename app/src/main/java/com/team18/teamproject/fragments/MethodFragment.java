package com.team18.teamproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.adapters.InstructionRVAdapter;
import com.team18.teamproject.extras.JsonParser;
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.network.VolleySingleton;
import com.team18.teamproject.pojo.Recipe;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fragment that displays the method for each recipe and the facebook share button.
 *
 * Facebook sharing function created by Alex.
 * Layout and RecyclerView created by Daniel.
 */
public class MethodFragment extends Fragment {

    private Recipe recipe;

    /**
     * Script URL
     */
    private final static String URL = Urls.GET_INSTRUCTIONS;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private InstructionRVAdapter adapter;
    private RecyclerView recyclerView;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    /**
     * Empty public constructor.
     */
    public MethodFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialise facebook SDK
        FacebookSdk.sdkInitialize(Application.getAppContext());

        // Initialise fields.
        recipe = Application.getCurrentRecipe();
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_method, container, false);

        // Set up RecyclerView.
        recyclerView = (RecyclerView) view.findViewById(R.id.instruction_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter with RecyclerView.
        adapter = new InstructionRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        // Retrieves instructions from favourites if they are available.
        // Otherwise, request ingredients from database.
        if (Application.getFavourites().keySet().contains(recipe.getId())
                && recipe.getInstructions().size() > 0) {
            adapter.setInstructionList(recipe.getInstructions());
        } else {
            sendJsonRequest();
        }

        setupListeners(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Sends a POST request for JSON data.
     * Populates the recycler view or shows an error as a snackbar message.
     */
    private void sendJsonRequest() {

        Map<String, String> params = new HashMap<>();
        params.put("RecipeID", recipe.getId() + "");

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, URL, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    List<String> instructions = JsonParser.parseJSONInstructionArray(array);
                    Application.getCurrentRecipe().setInstructions(instructions);

                    adapter.setInstructionList(instructions);

                } catch (JSONException e) {
                    // Display an error snackbar message.
                    Application.responseError(recyclerView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Display an error snackbar message.
                Application.connectionError(recyclerView);
            }
        });

        requestQueue.add(request);
    }

    /**
     * Sets up listeners for the Facebook button.
     *
     * @param view Inflated layout view.
     */
    private void setupListeners(View view) {
        LinearLayout button = (LinearLayout) view.findViewById(R.id.facebook_share_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAppInstalled("com.facebook.katana") && isOnline(getContext()) && isCameraAvailable(getContext())) {
                    takePhoto();
                } else if (!isCameraAvailable(getContext())) {
                    Context context = getContext();
                    CharSequence text = "No Camera Detected";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (!isOnline(getContext())) {
                    Context context = getContext();
                    CharSequence text = "Connection Error";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.katana")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.katana")));
                    }
                }
            }
        });
    }

    /**
     *  Returns true if camera is available.
     *
     * @param context Fragment context.
     * @return true if camera is available, false otherwise.
     */
    private static boolean isCameraAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    /**
     * Returns true if online.
     *
     * @param context Fragment context.
     * @return true if online.
     */
    private boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Test if another app is installed on the device.
     *
     * @param packageName Name of the package being tested for.
     */
    private boolean isAppInstalled(String packageName) {
        PackageManager pm = getContext().getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Builds intent for camera application and start camera activity.
     */
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    /**
     * Builds a Facebook SharePhotoContent to share on Facebook.
     *
     * @param bitmap Photo returned by camera activity
     */
    private void sharePhoto(Bitmap bitmap) {
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .setCaption("@string/share_message")
                .build();
        SharePhotoContent photoContent = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(photoContent);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            if (ShareDialog.canShow(SharePhotoContent.class)) {
                sharePhoto(bitmap);
            }
        } else {

        }
    }

}