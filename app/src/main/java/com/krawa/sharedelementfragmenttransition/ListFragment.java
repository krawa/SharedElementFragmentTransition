package com.krawa.sharedelementfragmenttransition;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment implements MessagesListAdapter.MessagesListCallbacks {

    private RecyclerView messagesList;
    private MessagesListAdapter adapter;

    public ListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        messagesList = (RecyclerView) view.findViewById(R.id.messagesList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messagesList.setLayoutManager(layoutManager);

        adapter = new MessagesListAdapter(DialogItem.getItems());
        adapter.setMessagesListCallbacks(this);
        messagesList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickMessage(DialogItem item, View view) {
        setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));
        setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.fade));

        // Create new fragment to add (Fragment B)
        DetailsFragment fragment = new DetailsFragment();
        fragment.setDialog(item);
        fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));
        fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.fade));

        // Our shared element (in Fragment A)
        View ava = view.findViewById(R.id.ivPhoto);
        ava.setTransitionName(DetailsFragment.AVA_TRANS);

        View name = view.findViewById(R.id.tvName);
        name.setTransitionName(DetailsFragment.NAME_TRANS);

        // Add Fragment B
        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .addSharedElement(ava, DetailsFragment.AVA_TRANS)
                .addSharedElement(name, DetailsFragment.NAME_TRANS);
        ft.commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity)getActivity()).getToolbar().setBackgroundColor(getResources().getColor(R.color.myPrimaryColor));
        ((MainActivity)getActivity()).getToolbar().setTitle("Dialogs");
    }
}
