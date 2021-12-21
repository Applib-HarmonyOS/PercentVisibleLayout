package com.example.percentvisiblelayout.views;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.example.percentvisiblelayout.PercentVisibleLayout;
import com.example.percentvisiblelayout.ResourceTable;



/**
 * Horizontal.
 */
public class Horizontal extends Ability {

    private static final String TAG = PercentVisibleLayout.class.getSimpleName();
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD000F00, TAG);

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_custom);



        PercentVisibleLayout customView = (PercentVisibleLayout) findComponentById(ResourceTable.Id_customview);
        DirectionalLayout linearlayout = new DirectionalLayout(getApplicationContext());
        PercentVisibleLayout customView1 = new PercentVisibleLayout(this, null);
        PercentVisibleLayout customView2 = new PercentVisibleLayout(this, null);
        PercentVisibleLayout customView3 = new PercentVisibleLayout(this, null);
        Text textview1 = new Text(getApplicationContext());
        Text textview2 = new Text(getApplicationContext());
        Text textview3 = new Text(getApplicationContext());
        ShapeElement red = new ShapeElement();
        ShapeElement blue = new ShapeElement();
        ShapeElement magenta = new ShapeElement();


        red.setRgbColor(RgbColor.fromArgbInt(Color.RED.getValue()));
        blue.setRgbColor(RgbColor.fromArgbInt(Color.BLUE.getValue()));
        magenta.setRgbColor(RgbColor.fromArgbInt(Color.MAGENTA.getValue()));



        linearlayout.setOrientation(Component.HORIZONTAL);
        linearlayout.setAlignment(LayoutAlignment.VERTICAL_CENTER);
        customView.addComponent(linearlayout, new ComponentContainer.LayoutConfig(
                ComponentContainer.LayoutConfig.MATCH_CONTENT, ComponentContainer.LayoutConfig.MATCH_PARENT));

        customView1.setBackground(red);
        customView1.setMarginTop(300);
        linearlayout.addComponent(customView1, new ComponentContainer.LayoutConfig(1000, 1000));

        textview1.setTextAlignment(TextAlignment.CENTER);
        textview1.setTextColor(Color.WHITE);
        textview1.setTextSize(75);
        customView1.addComponent(textview1, new ComponentContainer.LayoutConfig(
                ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT));

        customView2.setBackground(blue);
        customView2.setMarginTop(400);
        linearlayout.addComponent(customView2, new ComponentContainer.LayoutConfig(1000, 1000));

        textview2.setTextAlignment(TextAlignment.CENTER);
        textview2.setTextColor(Color.WHITE);
        textview2.setTextSize(75);
        customView2.addComponent(textview2, new ComponentContainer.LayoutConfig(
                ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT));

        customView3.setBackground(magenta);
        customView3.setMarginTop(400);
        linearlayout.addComponent(customView3, new ComponentContainer.LayoutConfig(1000, 1000));

        textview3.setTextAlignment(TextAlignment.CENTER);
        textview3.setTextColor(Color.WHITE);
        textview3.setTextSize(75);
        customView3.addComponent(textview3, new ComponentContainer.LayoutConfig(
                ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT));

        customView.getComponentTreeObserver().addScrolledListener(() -> {
            customView1.calculateVisibility();
            customView2.calculateVisibility();
            customView3.calculateVisibility();
        });


        customView1
                .setOnVisibilityPercentChangedListener((fromHeight, fromWidth, percentageHeight, percentageWidth) ->
                    textview1.setText(percentageWidth + "%")
                );

        customView2
                .setOnVisibilityPercentChangedListener((fromHeight, fromWidth, percentageHeight, percentageWidth) ->
                    textview2.setText(percentageWidth + "%")
                );

        customView3
                .setOnVisibilityPercentChangedListener((fromHeight, fromWidth, percentageHeight, percentageWidth) ->
                    textview3.setText(percentageWidth + "%")
                );


        customView1.setOnVisibilityPixelChangedListener(new PercentVisibleLayout.OnVisibilityPixelChanged() {
            @Override
            public void onVisibilityChange(int verticalClip, int horizontalClip, int pixelHeight, int pixelWidth) {
                HiLog.debug(LABEL_LOG, "%{public}s", "Width pixel: " + pixelWidth);
            }
        });
    }

}
