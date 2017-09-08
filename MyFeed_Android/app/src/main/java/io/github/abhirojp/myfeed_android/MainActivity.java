package io.github.abhirojp.myfeed_android;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import io.github.abhirojp.myfeed_android.callback.OnFeedItemClick;
import io.github.abhirojp.myfeed_android.data.DataModel;
import io.github.abhirojp.myfeed_android.fragment.FeedDetailsFragment;
import io.github.abhirojp.myfeed_android.fragment.FeedListFragment;

import static io.github.abhirojp.myfeed_android.data.Constants.FRAGMENT_TAG;
import static io.github.abhirojp.myfeed_android.data.Constants.fragtag;

public class MainActivity extends AppCompatActivity implements OnFeedItemClick{

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        fragmentManager=getSupportFragmentManager();
        if(savedInstanceState!=null && savedInstanceState.size()>0){
            Fragment fragment=fragmentManager.getFragment(savedInstanceState,FRAGMENT_TAG);
            if(fragment instanceof FeedListFragment){
            FeedListFragment listFragment=(FeedListFragment) fragment;
                fragmentManager.beginTransaction().add(R.id.feed_container, listFragment).addToBackStack(FeedListFragment.TAG).commit();
            }else if(fragment instanceof FeedDetailsFragment){
            FeedDetailsFragment detailsFragment=(FeedDetailsFragment) fragment;
                fragmentManager.beginTransaction().add(R.id.feed_container, detailsFragment).addToBackStack(FeedDetailsFragment.TAG).commit();
            }
        }else{
            FeedListFragment listFragment = FeedListFragment.newInstance();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Transition t1 = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
                Transition t2 = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
                t1.setInterpolator(new DecelerateInterpolator());
                t2.setInterpolator(new AccelerateInterpolator());
                listFragment.setEnterTransition(t1);
                listFragment.setExitTransition(t2);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                        .add(R.id.feed_container, listFragment);
                ft.commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.feed_container, listFragment).commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(fragmentManager!=null){
            int last=fragmentManager.getBackStackEntryCount()-1;
            if(last>0){
            String tag=fragmentManager.getBackStackEntryAt(last-1).getName();
                Fragment fragment=fragtag.get(tag);
                if(fragment!=null && fragment.isAdded()){
                    fragmentManager.putFragment(outState,FRAGMENT_TAG,fragment);
                }
            }
        }
    }

    @Override
    public void passData(DataModel d) {
        FeedDetailsFragment detailsFragment=FeedDetailsFragment.newInstance(d);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition t1 = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
            Transition t2 = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
            t1.setInterpolator(new DecelerateInterpolator());
            t2.setInterpolator(new AccelerateInterpolator());
            detailsFragment.setEnterTransition(t1);
            detailsFragment.setExitTransition(t2);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.feed_container, detailsFragment, FeedDetailsFragment.TAG)
                    .addToBackStack(FeedDetailsFragment.TAG);
            ft.commit();

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.feed_container, detailsFragment, FeedDetailsFragment.TAG).addToBackStack(FeedDetailsFragment.TAG).commit();
        }

    }
    
}
