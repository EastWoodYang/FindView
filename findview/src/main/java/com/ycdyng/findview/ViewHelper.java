package com.ycdyng.findview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewHelper implements IViewHelper<ViewHelper> {

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;

    private final Context context;

    private View rootView;

    public static ViewHelper bind(Activity activity) {
        View rootView = activity.getWindow().getDecorView();
        return new ViewHelper(activity, rootView);
    }

    public static ViewHelper bindView(Activity activity, int layoutResID) {
        activity.setContentView(layoutResID);
        View rootView = activity.getWindow().getDecorView();
        return new ViewHelper(activity, rootView);
    }

    public static ViewHelper bindView(Context context, View view) {
        return new ViewHelper(context, view);
    }

    private ViewHelper(Context context, View rootView) {
        this.context = context;
        this.views = new SparseArray<View>();
        this.rootView = rootView;
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getView() {
        return rootView;
    }

    @Override
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    @Override
    public ViewHelper setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public ViewHelper setText(int viewId, CharSequence value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public ViewHelper setText(int viewId, int valueId) {
        TextView view = retrieveView(viewId);
        view.setText(valueId);
        return this;
    }

    @Override
    public ViewHelper setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    @Override
    public ViewHelper setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    @Override
    public ViewHelper setBackgroundRes(int viewId, int backgroundRes) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    @Override
    public ViewHelper setTextColor(int viewId, int textColor) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    @Override
    public ViewHelper setTextColorRes(int viewId, int textColorRes) {
        TextView view = retrieveView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    @Override
    public ViewHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public ViewHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public ViewHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    @Override
    public ViewHelper setVisibility(int viewId, int visibility) {
        View view = retrieveView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    @Override
    public ViewHelper linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    @Override
    public ViewHelper setTypeface(int viewId, Typeface typeface) {
        TextView view = retrieveView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    @Override
    public ViewHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = retrieveView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    @Override
    public ViewHelper setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    @Override
    public ViewHelper setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    @Override
    public ViewHelper setMax(int viewId, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        return this;
    }

    @Override
    public ViewHelper setRating(int viewId, float rating) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        return this;
    }

    @Override
    public ViewHelper setRating(int viewId, float rating, int max) {
        RatingBar view = retrieveView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    @Override
    public ViewHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    @Override
    public ViewHelper setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = retrieveView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    @Override
    public ViewHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    @Override
    public ViewHelper setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }

    @Override
    public ViewHelper setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }

    @Override
    public ViewHelper setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    @Override
    public ViewHelper setTag(int viewId, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(tag);
        return this;
    }

    @Override
    public ViewHelper setTag(int viewId, int key, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(key, tag);
        return this;
    }

    @Override
    public ViewHelper setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) retrieveView(viewId);
        view.setChecked(checked);
        return this;
    }

    @Override
    public ViewHelper setAdapter(int viewId, Adapter adapter) {
        AdapterView view = retrieveView(viewId);
        view.setAdapter(adapter);
        return this;
    }

}
