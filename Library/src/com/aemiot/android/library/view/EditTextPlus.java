package com.aemiot.android.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.aemiot.android.library.R;

/**
 * @author aem3372  E-mail: aem3372@163.com
 * @version 0
 * 增强型EditText，支持右侧删除按钮
 * 使用本类，一旦配置完成请不要使用setCompoundDrawables系列函数,否则会抛出RuntimeException。
 * 属性集：
 *   {@link res/values/attrs.xml}
 * 禁用函数：
 *   setCompoundDrawablesWithIntrinsicBounds
 *   setCompoundDrawables
 * 已知缺陷报告：
 *   直接继承自EditText，但EditText职责过多，难以完全控制。（若从View派生以组合方式使用EditText会更完美？）
 *   setCompoundDrawablesRelative系列未被禁用
 */
public class EditTextPlus extends EditText {

    public EditTextPlus(Context context) {
        super(context);
    }

    public EditTextPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        flateAttribute(typedArray);
        typedArray.recycle();
    }

    public EditTextPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        flateAttribute(typedArray);
        typedArray.recycle();
    }

    public void setClearButtonDrawable(Drawable drawable) {
        clearButtonDrawable = drawable;
        flush();
    }
    
    public Drawable getClearButton() {
        return clearButtonDrawable;
    }
    
    public void setIconDrawable(Drawable drawable) {
        iconDrawable = drawable;
        flush();
    }
    
    public Drawable getIcon() {
        return iconDrawable;
    }
    
    public void setClearButtonSize(int width, int height) {
        clearButtonDrawableWidth = width;
        clearButtonDrawableHeight = height;
        flush();
    }
    
    public int getClearButtonWidth() {
        return clearButtonDrawableWidth;
    }
    
    public int getClearButtonHeight() {
        return clearButtonDrawableHeight;
    }
    
    public void setIconSize(int width, int height) {
        iconDrawableWidth = width;
        iconDrawableHeight = height;
        flush();
    }
    
    public int getIconWidth() {
        return iconDrawableWidth;
    }
    
    public int getIconHeight() {
        return iconDrawableHeight;
    }
    
    public void enableClearPlus() {
        clearPlusFlag = true;
        flush();
    }
    
    public void unableClearPlus() {
        clearPlusFlag = false;
        flush();
    }
    
    public void enableIconPlus() {
        iconPlusFlag = true;
        flush();
    }
    
    public void unableIconPlus() {
        iconPlusFlag = false;
        flush();
    }
    
    /**
     * 检测clearbutton，处理clear逻辑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clearPlusFlag && length() > 0 && isFocused() &&
                event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getX();
            int eventY = (int) event.getY();
            
            Rect rect = new Rect();
            rect.left = getRight() - getLeft() - getCompoundPaddingRight();
            rect.right = getRight() - getLeft();
            rect.top = (getBottom() - getTop() - getCompoundPaddingRight()) / 2;
            rect.bottom = (getBottom() - getTop() + getCompoundPaddingRight()) / 2;


            if(rect.contains(eventX, eventY)) {
                setText("");
                return true;
            }
        }
        return super.onTouchEvent(event);

    }
    
    /**
     * 文本发生改变，clearbutton状态改变
     */
    @Override
    protected void onTextChanged(CharSequence text, int start,
            int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setDrawable();
    }

    /**
     * 焦点发生改变，clearbutton状态改变
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction,
            Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDrawable(); 
    }

    /**
     * 大小确定，进行初始化
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        flush();
    }
    
    /**
     * 填充属性
     */
    private void flateAttribute(TypedArray typedArray) {
        clearButtonDrawable = typedArray.getDrawable(R.styleable.EditTextPlus_clearButton);
        clearButtonDrawableWidth = 
                typedArray.getDimensionPixelSize(R.styleable.EditTextPlus_clearButton_width, clearButtonDrawableWidth);
        clearButtonDrawableHeight = 
                typedArray.getDimensionPixelSize(R.styleable.EditTextPlus_clearButton_height, clearButtonDrawableHeight);
        
        iconDrawable = typedArray.getDrawable(R.styleable.EditTextPlus_icon);
        iconDrawableWidth = 
                typedArray.getDimensionPixelSize(R.styleable.EditTextPlus_icon_width, iconDrawableWidth);
        iconDrawableHeight = 
                typedArray.getDimensionPixelSize(R.styleable.EditTextPlus_icon_height, iconDrawableWidth);
        
        compoundDrawablePadding =
                typedArray.getDimensionPixelSize(R.styleable.EditTextPlus_compound_padding, compoundDrawablePadding);       
    }
    
    /**
     * 初始化
     */
    private void flush() {
        Drawable[] drawables = getCompoundDrawables();
        leftDrawable = drawables[0];
        topDrawable = drawables[1];
        rightDrawable = drawables[2];
        bottomDrawable = drawables[3];
        
        if(clearButtonDrawable != null) {
            clearButtonDrawable.setBounds(0, 0, 
                    clearButtonDrawableWidth == 0?
                            clearButtonDrawable.getIntrinsicWidth() : clearButtonDrawableWidth,
                    clearButtonDrawableHeight == 0?
                            clearButtonDrawable.getIntrinsicHeight() : clearButtonDrawableHeight);
        }
        
        if(iconDrawable != null) {
            iconDrawable.setBounds(0, 0, 
                    iconDrawableWidth == 0?
                            iconDrawable.getIntrinsicWidth() : iconDrawableWidth,
                    iconDrawableHeight == 0?
                            iconDrawable.getIntrinsicHeight() : iconDrawableHeight);
        }
        
        setDrawable();
        initFinishFlag = true;
    }
    
    /**
     * 显示加强功能
     */
    private void setDrawable() {
        
        if(iconPlusFlag) {
            leftDrawable = iconDrawable;
        }
        else {
            leftDrawable = null;
        }

        if(clearPlusFlag && length() > 0 && isFocused()) {
            rightDrawable = clearButtonDrawable;
        }
        else {
            rightDrawable = null;
        }
        setCompoundDrawablePadding(compoundDrawablePadding);
        super.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable);  
    }

    /**
     * 阻止继承的setCompoundDrawables函数被外部调用
     * 当初始化完成后调用该函数将抛出RuntimeException
     */
    @Override
    public void setCompoundDrawables(Drawable left, Drawable top,
            Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);
        if(initFinishFlag)
            throw new RuntimeException("Call no supported function in " + this.getClass().getName());
    }
    
    /**
     * 阻止继承的setCompoundDrawablesWithIntrinsicBounds函数被外部调用
     * 当初始化完成后调用该函数将抛出RuntimeException
     */
    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top,
            int right, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if(initFinishFlag)
            throw new RuntimeException("Call no supported function in " + this.getClass().getName());
    }
    
    /**
     * 阻止继承的setCompoundDrawablesWithIntrinsicBounds函数被外部调用
     * 当初始化完成后调用该函数将抛出RuntimeException
     */
    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
            Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if(initFinishFlag)
            throw new RuntimeException("Call no supported function in " + this.getClass().getName());
    }
    
    /*View Status Flag*/
    private boolean initFinishFlag = false;
    
    /*Plus Flag*/
    private boolean clearPlusFlag = true;
    private boolean iconPlusFlag = true;
    
    /*Drawable Attribute*/
    private int clearButtonDrawableWidth = 0;
    private int clearButtonDrawableHeight = 0;
    private int iconDrawableWidth = 0;
    private int iconDrawableHeight = 0;
    private int compoundDrawablePadding = 8;
    
    private Drawable iconDrawable = null;
    private Drawable clearButtonDrawable = null;
    
    private Drawable leftDrawable = null;
    private Drawable topDrawable = null;
    private Drawable rightDrawable = null;
    private Drawable bottomDrawable = null;
}
