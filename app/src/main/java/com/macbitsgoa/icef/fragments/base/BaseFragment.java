package com.macbitsgoa.icef.fragments.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.macbitsgoa.icef.BaseActivityCallback;
import com.macbitsgoa.icef.R;
import com.macbitsgoa.icef.ViewHolders.ProgressBarHandler;


public class BaseFragment extends Fragment {


    private BaseActivityCallback callback;
    private ProgressBarHandler progressBarHandler;
    private Snackbar snack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBarHandler = new ProgressBarHandler(getActivity());

    }

    protected void showProgressDialog(String message) {
        if (callback != null)
            callback.showProgressDialog(message, getContext());
    }

    protected void hideProgressDialog() {
        if (callback != null)
            callback.hideProgressDialog();
    }

    public void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public void showToast(String message, int toastType) {
        Toast.makeText(getContext(), message, toastType).show();
    }

    public void showSnack(String message) {
        showSnack(message, Snackbar.LENGTH_LONG, getActivity().getWindow().getDecorView());
    }

    public void showSnack(String message, int length, View view) {
        try {
            snack = Snackbar.make(view, message, length);

        } catch (Exception e) {
            Log.d("Snack Error", e.getMessage());
            snack = Snackbar.make(getActivity().getCurrentFocus(), message, length);
        }
        TextView snackBarText = snack.getView().findViewById(R.id.snackbar_text);
        snackBarText.setTextColor(Color.WHITE);
        snack.getView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.snackBar));
        snack.show();

    }

    public boolean showSnack() {
        showSnack("Cannot fetch data");
        return true;
    }

    public Snackbar getSnack() {
        return snack;
    }


    protected void setToolbarTitle(String title) {
        callback.setToolbarTitle(title);
    }

    protected void showProgressBar() {
        progressBarHandler.show();
    }

    protected void hideProgressBar() {
        progressBarHandler.hide();
    }

    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

}
