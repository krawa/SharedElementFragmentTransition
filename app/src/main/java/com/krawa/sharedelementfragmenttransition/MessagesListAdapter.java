package com.krawa.sharedelementfragmenttransition;


import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.DialogViewHolder> {

    private MessagesListCallbacks messagesListCallbacks;
    private SortedList<DialogItem> mDialogList;

    public MessagesListAdapter(ArrayList<DialogItem> items) {
        mDialogList = new SortedList<>(DialogItem.class, new SortedListAdapterCallback<DialogItem>(this) {
            @Override
            public int compare(DialogItem d1, DialogItem d2) {
                Long date1 = 0l;
                Long date2 = 0l;
                if (d1 != null) {
                    date1 = d1.getDate();
                }
                if (d2 != null) {
                    date2 = d2.getDate();
                }
                return date2.compareTo(date1);

            }

            @Override
            public boolean areContentsTheSame(DialogItem oldItem, DialogItem newItem) {
                // return whether the items' visual representations are the same or not.
                if(!oldItem.getTitle().equals(newItem.getTitle()))
                    return false;
                if(!oldItem.getMessage().equals(newItem.getMessage()))
                    return false;
                if(oldItem.getDate() != newItem.getDate())
                    return false;
                if(oldItem.getUnread() != newItem.getUnread())
                    return false;
                return true;
            }

            @Override
            public boolean areItemsTheSame(DialogItem item1, DialogItem item2) {
                return item1.getDid().equals(item2.getDid());
            }
        });
        addAll(items);
    }

    @Override
    public DialogViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.messages_list_item, viewGroup, false);
        final DialogViewHolder dialogViewHolder = new DialogViewHolder(view);
        dialogViewHolder.itemView.setClickable(true);
        dialogViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             if (messagesListCallbacks != null)
                                                                 messagesListCallbacks.onClickMessage(mDialogList.get(dialogViewHolder.getAdapterPosition()), v);
                                                         }
                                                     }
        );
        return dialogViewHolder;
    }

    @Override
    public void onBindViewHolder(DialogViewHolder dialogViewHolder, int pos) {
        DialogItem currentDialog = mDialogList.get(pos);

        int imageSize = dialogViewHolder.photo.getLayoutParams().width;
        Picasso p = Picasso.with(dialogViewHolder.photo.getContext().getApplicationContext());
        p.load(currentDialog.getPhoto())
                .resize(imageSize, imageSize)
                .transform(new RoundedTransformation((int) (imageSize * 0.5f), 0))
                .centerCrop()
                .into(dialogViewHolder.photo);

        dialogViewHolder.body.setText(currentDialog.getMessage());
        dialogViewHolder.name.setText(currentDialog.getTitle());
        dialogViewHolder.date.setText(DateUtils.getInstance().getDateTimeString(new Date(currentDialog.getDate() * 1000)));
    }

    @Override
    public int getItemCount() {
        return mDialogList.size();
    }

    public void setMessagesListCallbacks(MessagesListCallbacks messagesListCallbacks) {
        this.messagesListCallbacks = messagesListCallbacks;
    }
    public DialogItem get(int position) {
        return mDialogList.get(position);
    }

    public int add(DialogItem item) {
        return mDialogList.add(item);
    }

    public void updateItemAt(int index, DialogItem item) {
        mDialogList.updateItemAt(index, item);
    }

    public void addAll(List<DialogItem> items) {
        mDialogList.beginBatchedUpdates();
        for (DialogItem item : items) {
            int pos = SortedList.INVALID_POSITION;
            for(int i = 0; i < mDialogList.size(); i++){
                if(item.getDid().equals(get(i).getDid())){
                    pos = i;
                    break;
                }
            }
            if(pos != SortedList.INVALID_POSITION){
                updateItemAt(pos, item);
            }else{
                add(item);
            }
        }
        mDialogList.endBatchedUpdates();
    }

    class DialogViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView name;
        public TextView body;
        public TextView date;

        public DialogViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.ivPhoto);
            name = (TextView) itemView.findViewById(R.id.tvName);
            body = (TextView) itemView.findViewById(R.id.tvBody);
            date = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }

    public interface MessagesListCallbacks{
        void onClickMessage(DialogItem item, View view);
    }
}
