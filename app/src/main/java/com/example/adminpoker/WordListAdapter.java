package com.example.adminpoker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;
    private onTaskListener mOnTaskListener;

    public WordListAdapter(Context context,
                           LinkedList<String> wordList, onTaskListener onTaskListener) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mOnTaskListener = onTaskListener;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.question_item,
                parent, false);
        return new WordViewHolder(mItemView, this, mOnTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        public final Switch mySwitch;
        final WordListAdapter mAdapter;
        onTaskListener onTaskListener;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter, onTaskListener onTaskListener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            mySwitch = itemView.findViewById(R.id.simpleSwitch);
            this.mAdapter = adapter;
            this.onTaskListener = onTaskListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskListener.onTaskClick(getAdapterPosition());
        }
    }

    public interface onTaskListener {
        void onTaskClick(int position);
    }
}


