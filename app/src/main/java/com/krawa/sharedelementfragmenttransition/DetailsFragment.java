package com.krawa.sharedelementfragmenttransition;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment{

    public static final String AVA_TRANS = "ava_transition";
    public static final String NAME_TRANS = "name_transition";

    private DialogItem dialog;
    private TextView name;
    private ImageView photo;

    public DetailsFragment(){}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            MainActivity act = (MainActivity) getActivity();
            act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Toolbar toolbar = act.getToolbar();
            toolbar.setTitle("");
            toolbar.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        photo = (ImageView) view.findViewById(R.id.ivPhoto);
        name = (TextView) view.findViewById(R.id.tvName);

        setUserInfo();

        return view;
    }


    private void setUserInfo() {
        if(dialog != null){
            int imageSize = photo.getLayoutParams().width;
            Picasso p = Picasso.with(photo.getContext().getApplicationContext());
            p.load(dialog.getPhoto())
                    .resize(imageSize, imageSize)
                    .transform(new RoundedTransformation((int) (imageSize * 0.5f), 0))
                    .centerCrop()
                    .into(photo);

            name.setText(dialog.getTitle());
        }
    }

    public void setDialog(DialogItem dialog){
        this.dialog = dialog;
    }


}
