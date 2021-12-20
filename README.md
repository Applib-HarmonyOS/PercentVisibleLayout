# PercentVisibleLayout

PercentVisibleLayout is a layout (extends ScrollView) that can be used to calculate the visibility in percentage and pixel. A scroll listener can be added in the parent custom view that will be invoked whenever scroll happen. Based on that the visibility percentage of the children custom layout can be updated every time. And a custom callback method is there to listen if any changes happen. Pixel visibility listener is also available.

### Currently supporting
- Use **ONLY** inside PercentVisibleLayout.
- Percentage Listener with visible height/width percentage and flags for which part is missing
- Pixels Listener with visible height/width pixels and flags for which part is missing



![picture](/images/vertical.png)
![picture](/images/horizontal.png)



### Usage



#### In your xml file

```xml
  <?xml version="1.0" encoding="utf-8"?>
  <DirectionalLayout
      xmlns:ohos="http://schemas.huawei.com/res/ohos"
      ohos:height="match_parent"
      ohos:width="match_parent"
      ohos:alignment="center">
  
      <com.example.percentvisiblelayout.PercentVisibleLayout
          ohos:id="$+id:customview"
          ohos:height="match_parent"
          ohos:width="match_parent">
  
      </com.example.percentvisiblelayout.PercentVisibleLayout>
  </DirectionalLayout>
```
#### In your activity

```java

           PercentVisibleLayout customView = (PercentVisibleLayout) findComponentById(ResourceTable.Id_customview);
   
   
           DirectionalLayout linearlayout = new DirectionalLayout(getApplicationContext());
           linearlayout.setOrientation(Component.VERTICAL);
           linearlayout.setAlignment(LayoutAlignment.HORIZONTAL_CENTER);
           customView.addComponent(linearlayout, new ComponentContainer.LayoutConfig(
                   ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_CONTENT));
   
           PercentVisibleLayout customView1 = new PercentVisibleLayout(this, null);
           ShapeElement red = new ShapeElement();
           red.setRgbColor(RgbColor.fromArgbInt(Color.RED.getValue()));
           customView1.setBackground(red);
           customView1.setMarginTop(300);
           linearlayout.addComponent(customView1, new ComponentContainer.LayoutConfig(1000, 1000));
   
           Text textview1 = new Text(getApplicationContext());
           textview1.setTextAlignment(TextAlignment.CENTER);
           textview1.setTextColor(Color.WHITE);
           textview1.setTextSize(75);
           customView1.addComponent(textview1, new ComponentContainer.LayoutConfig(
                   ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT));


           customView.getComponentTreeObserver().addScrolledListener(() -> {
               customView1.calculateVisibility();
           });
           
           customView1.setOnVisibilityPercentChangedListener((fromHeight, fromWidth, percentageHeight, percentageWidth) ->
               textview1.setText(percentageHeight + "%")
           );
```


### Source

---
This library has been inspired by [tzanou/PercentVisibleLayout](https://github.com/tzanou/PercentVisibleLayout)



### Integration

---

**From Source**
1. For using PercentVisibleLayout module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
    ```groovy
    implementation project(path: ':percentvisiblelayout')
    ```
2. For using PercentVisibleLayout module in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
    ```groovy
   implementation fileTree(dir: 'libs', include: ['*.har'])


   
   
### License

TitleLayout is released under the [Apache License Version 2.0](LICENSE).

