package com.example.jordan.imageslider;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jordan on 5/13/2016.
 */
public class ViewPage extends Fragment{

    View view;
    static int NUM_ITEMS = 0;
    ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    public static ArrayList<Bitmap> images;
    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pager, container, false);
        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(((HomeScreen)getActivity()).getSupportFragmentManager());
        ((HomeScreen)getActivity()).setContentView(R.layout.fragment_pager);
        viewPager = (ViewPager) ((HomeScreen)getActivity()).findViewById(R.id.pager);
        viewPager.setAdapter(imageFragmentPagerAdapter);

        images = new ArrayList<Bitmap>();

        ImageView addButton = (ImageView) ((HomeScreen)getActivity()).findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeScreen)getActivity()).addImage();
            }
        });
        ImageView camButton = (ImageView) ((HomeScreen)getActivity()).findViewById(R.id.cam_button);
        assert camButton != null;
        camButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((HomeScreen)getActivity()).takeImage();
            }
        });
        return view;
    }

    public static class SwipeFragment extends android.support.v4.app.Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View swipeView = inflater.inflate(R.layout.swipe_fragment, container, false);
            ImageView imageView = (ImageView) swipeView.findViewById(R.id.imageView);
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");
            imageView.setImageBitmap(images.get(position));
            return swipeView;
        }

        static SwipeFragment newInstance(int position) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            swipeFragment.setArguments(bundle);
            return swipeFragment;
        }
    }

    public static class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
        public ImageFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return SwipeFragment.newInstance(position);
        }
    }

    public void addImage(String path){
        File img = new File(path);
        if (img.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            images.add(myBitmap);
            NUM_ITEMS++;
            imageFragmentPagerAdapter.notifyDataSetChanged();
        }
    }
}
