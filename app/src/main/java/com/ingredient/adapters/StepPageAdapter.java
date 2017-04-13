package com.ingredient.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ingredient.PicassoTool;
import com.ingredient.R;
import com.ingredient.objects.recipeModel.Step;
import com.lb.auto_fit_textview.AutoResizeTextView;

import java.util.ArrayList;
import java.util.List;




public class StepPageAdapter extends PagerAdapter {
    private List<View> views = new ArrayList<>();
    private List<Step> steps;
    private LayoutInflater layoutInflater;
    private Context context;
    private boolean editableMode;
    private ViewPager viewPager;
    ImageView iBackStep;
    ImageView iNextStep;
    ImageView iAddStep;

    public StepPageAdapter(@NonNull Context context, @NonNull List<Step> steps) {
        this(context,steps,null,null,null,null);
    }
    public StepPageAdapter(@NonNull Context context, @NonNull List<Step> steps,ViewPager viewPager,
                           ImageView iBackStep,ImageView iNextStep,ImageView iAddStep) {

        this.iBackStep = iBackStep;
        this.iNextStep = iNextStep;
        this.iAddStep = iAddStep;
        this.context = context;
        this.steps = steps;
        this.editableMode = viewPager != null;
        this.viewPager = viewPager;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0;i < steps.size();i++){
            views.add(null);
        }

    }

    @Override
    public int getCount() {
        return null == steps ? 0 : steps.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View v;
        if (views.get(position)!= null){
            if (((ViewGroup)views.get(position)).getChildAt(0) != null) {
                v = views.get(position);
            }else {
                v = getView(position);
                views.remove(position);
                views.add(position,v);
            }
        }else {
            v = getView(position);
            views.remove(position);
            views.add(position,v);
        }
        container.addView(v);
        return v;
    }
    private View getView(final int position){
        View v = layoutInflater.inflate(R.layout.step_page, viewPager,false);
        ImageView iStep = (ImageView)v.findViewById(R.id.iStep);
        TextView tStepNumber = (TextView)v.findViewById(R.id.tStepNumber);
        final AutoResizeTextView tDescription = (AutoResizeTextView)v.findViewById(R.id.tDescription);

        tDescription.setMinTextSize(8);
        if (steps.get(position).getImage() != null && !editableMode) {
            PicassoTool.load(context,steps.get(position).getImage(),iStep);
        }
        tStepNumber.setText(String.format(context.getResources().getString(R.string.step), position + 1));
        tDescription.setText(steps.get(position).getDescription());
        if (editableMode) {
            ProgressBar progressUploadImage = (ProgressBar)v.findViewById(R.id.progressUploadImage);
            if (steps.get(position).isUpload()){
                progressUploadImage.setVisibility(View.VISIBLE);
            }
            if (steps.get(position).getDescription().equals("")){
                tDescription.setText("+ Добавить описание");
            }
            if (steps.get(position).getBitmap() != null){
                iStep.setImageBitmap(steps.get(position).getBitmap());
            }else if (steps.get(position).getImage() != null){
                PicassoTool.load(context,steps.get(position).getImage(),iStep);
            }
            ImageView iDeleteStep = (ImageView)v.findViewById(R.id.iDeleteStep);
            iDeleteStep.setVisibility(View.VISIBLE);
            iDeleteStep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeStep(position);
                }
            });
            tDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(String.format(context.getResources().getString(R.string.step), position + 1));
                    final EditText input = new EditText(context);
                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    input.setText(steps.get(position).getDescription().equals("+ Добавить описание") ? "":steps.get(position).getDescription());
                    builder.setView(input);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            steps.get(position).setDescription(input.getText().toString());
                            tDescription.setText(input.getText());
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            });
            iStep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    ((Activity)context).startActivityForResult(intent, position);
                }
            });
        }
        return v;
    }
    public void addStep(Step step){
        steps.add(step);
        views.add(getView(steps.indexOf(step)));
        notifyDataSetChanged();
    }
    private void removeStep(int position){
        steps.remove(position);
        views.remove(position);
        viewPager.setAdapter(this);
        if (position > 0) {
            viewPager.setCurrentItem(position - 1);
        }

        if (position == 0){
            if (steps.size() <= 1) {
                iAddStep.setVisibility(View.VISIBLE);
                iNextStep.setVisibility(View.GONE);
                iBackStep.setVisibility(View.GONE);
            }else {
                iAddStep.setVisibility(View.GONE);
                iNextStep.setVisibility(View.VISIBLE);
                iBackStep.setVisibility(View.GONE);
            }
        }else if (position == 1){
            iBackStep.setVisibility(View.GONE);
        }
    }

    public List<View> getViews() {
        return views;
    }
    public List<Step> getSteps(){
        return steps;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((View) object);

            unbindDrawables((View) object);
            object = null;
        } catch (Exception e) {
            Log.e("StepPageAdapter", "destroyItem: failed to destroy item and clear it's used resources", e);
        }
    }


    protected void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}
