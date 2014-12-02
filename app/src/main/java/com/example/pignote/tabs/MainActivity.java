package com.example.pignote.tabs;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class MainActivity extends FragmentActivity
        implements FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener {
    private final String FIRST_TAB_TAG = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setupTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupTabs() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        FragmentTabListener ftl =
                new FragmentTabListener<FirstFragment>(R.id.sample_content_fragment, this, FIRST_TAB_TAG, FirstFragment.class);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Saves")
                .setTabListener(ftl);
        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);


        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Loads")
                .setTabListener(
                        new FragmentTabListener<SecondFragment>(R.id.sample_content_fragment, this, "second",
                                SecondFragment.class));
        actionBar.addTab(tab2);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onFragmentInteraction2(Uri uri) {
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void saveText(View v) {
        EditText editText = null;
        String putSave = "";

        switch (v.getId()) {
            case (R.id.imageButton):
                editText = (EditText) this.findViewById(R.id.editText);
                putSave = "tab_edittext_value";
                break;
            case (R.id.imageButton2):
                editText = (EditText) this.findViewById(R.id.editText2);
                putSave = "tab_edittext2_value";
                break;
            case (R.id.imageButton3):
                editText = (EditText) this.findViewById(R.id.editText3);
                putSave = "tab_edittext3_value";
                break;
            default:
                editText = (EditText) this.findViewById(R.id.editText);
                putSave = "tab_edittext_value";
                break;
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(putSave, editText.getText().toString());
        editor.commit();
    }

    public void loadText(View v) {
        EditText editText = null;
        String putSave = "";

        switch (v.getId()) {
            case (R.id.imageButton):
                editText = (EditText) this.findViewById(R.id.editText);
                putSave = "tab_edittext_value";
                break;
            case (R.id.imageButton2):
                editText = (EditText) this.findViewById(R.id.editText2);
                putSave = "tab_edittext2_value";
                break;
            case (R.id.imageButton3):
                editText = (EditText) this.findViewById(R.id.editText3);
                putSave = "tab_edittext3_value";
                break;
            default:
                editText = (EditText) this.findViewById(R.id.editText);
                putSave = "tab_edittext_value";
                break;
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editText.setText(sharedPref.getString(putSave, "Error: value not found!"));
    }
}
