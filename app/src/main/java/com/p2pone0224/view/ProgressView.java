package com.p2pone0224.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.p2pone0224.R;
import com.p2pone0224.utils.UIUtils;

/**
 * 作者：田学伟 on 2017/6/23 15:27
 * QQ：93226539
 * 作用：
 */

public class ProgressView extends View {

    private Paint paint;
    private int paintColor;
    private int strokeWidth = UIUtils.dp2px(10);
    private int height;
    private int width;
    private int sweepAngle = 0;
    private int textColor;

    public ProgressView(Context context) {
        super(context);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        /**
         *三种样式
         * stroke描边
         * fill填充
         * stroke and fill 既描边又填充
         */
        paint.setStyle(Paint.Style.STROKE);//设置圆环填充的样式
    }

    /**
     * 自定义属性:
     * 1.创建attrs文件
     * 2.在自定义控件构造器方法中,实例化attrs对象并获取属性名称和默认值
     * 3.在布局文件中使用自定义属性
     *
     * @param context
     * @param attrs
     */
    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progressView);
        int color = typedArray.getColor(R.styleable.progressView_paintColor, Color.BLACK);
        this.paintColor = color;
        int tc = typedArray.getColor(R.styleable.progressView_textColor, Color.BLUE);
        textColor = tc;
        //释放资源数据
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = getHeight();
        width = getWidth();
    }

    /**
     * 画出三个部分
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(strokeWidth);//画笔的宽度
        paint.setColor(paintColor);//圆环的颜色

        //画圆
        int cx = width / 2;//圆心的x坐标
        int cy = height / 2;//圆心的y坐标

        int radius = cx - strokeWidth / 2;//圆环的半径
        canvas.drawCircle(cx, cy, radius, paint);

        //画弧
        paint.setColor(Color.RED);//弧度的颜色
        RectF rectF = new RectF();
        //圆弧的坐上顶点和右下顶点
        rectF.set(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2);
        canvas.drawArc(rectF, 0, sweepAngle, false, paint);

        //画文字
        paint.setColor(textColor);
        paint.setStrokeWidth(0);//设置画笔的宽度
        String str = sweepAngle + "%";
        paint.setTextSize(30);//设置文字的大小
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);//获取文字的宽高
        int textWidth = rect.width();
        int textHeight = rect.height();
        float x = width / 2 - textWidth / 2; //左下顶点的x坐标
        float y = height / 2 + textHeight / 2;//左下顶点的y坐标
        canvas.drawText(str, x, y, paint);
    }

    /**
     * 面试的问题:invalidate和postInvalidate的区别是什么?
     * invalidate是主线程进行强制重绘
     * postInvalidate是分线程进行强制重绘
     * <p>
     * android 中动画有几种?
     * 1.属性动画:真正改变了控件的属性
     * 2.帧动画:把多张图片串联起来,实现连续播放的效果
     * 3.视图动画:普通动画虽然控件位置或者大小发生了变化,
     * 但是属性并没有真正发生改变,控件的监听的位置并无发生改变
     *
     * @param sweepAngle
     */
    public void setSweepAngle(int sweepAngle) {
        ValueAnimator animator = ValueAnimator.ofInt(0, sweepAngle);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int a = (int) animation.getAnimatedValue();

                ProgressView.this.sweepAngle = a;
                invalidate();
            }
        });
        animator.start();
    }
}

