package com.compsci.lauryn.geekymath;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

public class Learn extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.main_menu){//Adds the button to go to MainMenu and clears streak
            Intent intent=new Intent(this,MainMenu.class);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_learn, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.learnText);
            TextView textView1 = (TextView) rootView.findViewById(R.id.Place1);
            TextView textView2 = (TextView) rootView.findViewById(R.id.Place2);
            TextView textView3 = (TextView) rootView.findViewById(R.id.Place3);
            TextView textView4 = (TextView) rootView.findViewById(R.id.Place4);

            ImageView image1= (ImageView) rootView.findViewById(R.id.Image1);
            ImageView image2= (ImageView) rootView.findViewById(R.id.Image2);

            //ImageView imageView;

            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
                textView.setText(getString(R.string.intro));
                image1.setImageResource(android.R.color.transparent);
                image2.setImageResource(android.R.color.transparent);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==2) {
                textView.setText(getString(R.string.decimalsystem));
                textView1 = (TextView) rootView.findViewById(R.id.Place1);
                textView1.setText(getString(R.string.fivehundredtwelve));
                textView1.setTextSize(30);
                textView2.setText(getString(R.string.decimalsystem2));
                textView2.setTextSize(20);
                //Image:512
                //image2.setImage
                image1.setImageResource(android.R.color.transparent);
                image2.setImageResource(R.drawable.pic_of_num);

                textView3.setText(getString(R.string.decimalsystem3));
                textView3.setTextSize(20);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3) {
                image1.setImageResource(android.R.color.transparent);
                image2.setImageResource(android.R.color.transparent);

                textView.setText(getString(R.string.reading1));
                textView1.setText(getString(R.string.fivehundredtwelve));
                textView1.setTextSize(30);
                textView2.setText(getString(R.string.reading2));
                textView2.setTextSize(20);
                textView3.setText(getString(R.string.ten));
                textView3.setTextSize(30);
                textView4.setText(getString(R.string.reading3));
                textView4.setTextSize(20);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==4) {
                textView.setText(getString(R.string.bases));
                //Image:Chart
                //image1.setImage
                image2.setImageResource(android.R.color.transparent);
                image1.setImageResource(R.drawable.graph);

                textView1.setText(getString(R.string.bases2));
                textView1.setTextSize(20);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==5) {
                image1.setImageResource(android.R.color.transparent);
                image2.setImageResource(android.R.color.transparent);
                textView.setText(getString(R.string.practiceProblems));
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==6) {
                image1.setImageResource(android.R.color.transparent);
                image2.setImageResource(android.R.color.transparent);
                textView.setText(getString(R.string.finishingup));
            }
            else
                textView.setText(getString(R.string.invalid));
            textView.setTextSize(20);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
                case 5:
                    return "SECTION 6";
            }
            return null;
        }
    }
}
