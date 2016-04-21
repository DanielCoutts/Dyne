package com.team18.teamproject.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team18.teamproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Custom RecyclerView Adapter.
 */
public class InstructionRVAdapter extends RecyclerView.Adapter<InstructionRVAdapter.viewHolderInstruction> {

    /**
     * List of instructions to display in the RecyclerView.
     */
    private List<String> instructions = new ArrayList<>();

    /**
     * The LayoutInflater object for the fragment.
     */
    private LayoutInflater layoutInflater;

    /**
     * The fragment context.
     */
    private Context context;

    /**
     * Constructor that initialises the context and layoutInflater.
     *
     * @param context context of the parent.
     */
    public InstructionRVAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Initialises recipe list with specified list object.
     *
     * @param instructions List of instructions to display.
     */
    public void setInstructionList(List<String> instructions) {
        this.instructions = instructions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return instructions.size();
    }

    @Override
    public viewHolderInstruction onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.card_instruction, viewGroup, false);
        return new viewHolderInstruction(view);
    }

    @Override
    public void onBindViewHolder(viewHolderInstruction viewHolder, int i) {
        final String currentInstruction = instructions.get(i);
        viewHolder.instructionNumber.setText(String.format(Locale.UK, "%d", (i+1)));
        viewHolder.instruction.setText(currentInstruction);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Inner ViewHolder class.
     */
    public static class viewHolderInstruction extends RecyclerView.ViewHolder {

        TextView instructionNumber;
        TextView instruction;

        /**
         * Constructor that initialises fields.
         *
         * @param itemView View that will display the data.
         */
        public viewHolderInstruction(View itemView) {
            super(itemView);
            instructionNumber = (TextView) itemView.findViewById(R.id.card_instruction_number);
            instruction = (TextView) itemView.findViewById(R.id.card_instruction);
        }
    }
}
