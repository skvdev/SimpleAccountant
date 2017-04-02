package skv.org.simpleaccountant;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cora32 on 01.04.2017.
 */

public class PlotView extends View {
    private static CornerPathEffect cornerPathEffect;
    private float stroke_width;
    private float corner_path_radius;
    private int line_color;
    private int grid_line_color;
    private Paint paintLine;
    private Paint gridPaint;
    private Paint gridTextPaint;
    private Context mContext;
    private float gridTextSize;
    private float halfGridTextSize;
    private float leftPadding;
    private float rightPadding;
    private float topPadding;
    private float bottomPadding;
    private float mMin;
    private float mMax;
    private float mHeight;
    private float mWidth;
    private float x2Offset;
    private float y2Offset;
    private int gridLines;
    private PlotData data;
    private Rect textBounds = new Rect();

    public PlotView(Context context) {
        super(context);
        init(context, null);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context.getApplicationContext();

        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.PlotView);
        gridTextSize = getResources().getDimension(R.dimen.grid_text_size);
        halfGridTextSize = gridTextSize / 2f;
        stroke_width = arr.getDimension(R.styleable.PlotView_stroke_width, getResources().getDimensionPixelSize(R.dimen.stroke_width));
        corner_path_radius = arr.getDimension(R.styleable.PlotView_corner_path_radius, getResources().getDimensionPixelSize(R.dimen.corner_path_radius));
        leftPadding = arr.getDimension(R.styleable.PlotView_left_padding, getResources().getDimension(R.dimen.plot_left_padding));
        rightPadding = arr.getDimension(R.styleable.PlotView_right_padding, getResources().getDimension(R.dimen.plot_right_padding));
        topPadding = arr.getDimension(R.styleable.PlotView_top_padding, getResources().getDimension(R.dimen.plot_top_padding));
        bottomPadding = arr.getDimension(R.styleable.PlotView_bottom_padding, getResources().getDimension(R.dimen.plot_bottom_padding));
        float padding = arr.getDimension(R.styleable.PlotView_padding, -1f);
        if (padding > 0) {
            leftPadding = padding;
            rightPadding = padding;
            topPadding = padding;
            bottomPadding = padding;
        }
        line_color = arr.getColor(R.styleable.PlotView_line_color, ContextCompat.getColor(mContext, R.color.line_color));
        grid_line_color = arr.getColor(R.styleable.PlotView_grid_line_color, ContextCompat.getColor(mContext, R.color.grid_line_color));
        gridLines = arr.getInt(R.styleable.PlotView_grid_lines, getResources().getInteger(R.integer.grid_lines));
        arr.recycle();

        x2Offset = mWidth - leftPadding - rightPadding;
        y2Offset = mHeight - topPadding - bottomPadding;

        cornerPathEffect = new CornerPathEffect(corner_path_radius);

        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setDither(true);
        paintLine.setStrokeWidth(stroke_width);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setPathEffect(cornerPathEffect);
        paintLine.setColor(line_color);

        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setDither(true);
        gridPaint.setStrokeWidth(getResources().getDimension(R.dimen.grid_stroke_width));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setPathEffect(cornerPathEffect);
        gridPaint.setColor(grid_line_color);

        gridTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridTextPaint.setDither(true);
        gridTextPaint.setColor(ContextCompat.getColor(mContext, R.color.grid_text_color));
        gridTextPaint.setTextSize(gridTextSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHeight = getHeight();
        mWidth = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.clipRect(leftPadding, topPadding, mWidth - leftPadding - rightPadding, mHeight - topPadding - bottomPadding, Region.Op.REPLACE);
        canvas.scale(1, 0.9f);
        drawGrid(canvas);
        canvas.drawLine(0, 0, 100, 100, paintLine);
        canvas.drawLine(0, 0, 100, mHeight, paintLine);
//        drawLine(canvas, 0, 0, 100, 100, paintLine);
    }

    private void drawLine(Canvas canvas, float x1, float y1, float x2, float y2, Paint paint) {
        canvas.drawLine(leftPadding + x1, y1 - topPadding, x2Offset + x2, y2Offset + y2, paint);
    }

    private void drawText(Canvas canvas, String text, float x, float y, Paint paint) {
        canvas.drawText(text, leftPadding + x, topPadding + y, paint);
    }

    private void drawGrid(Canvas canvas) {
        float value = mHeight;
        float step = (mHeight - 20) / (float) gridLines;
        float textValue = mMin;
        String text = String.valueOf(textValue);
        String textMax = String.valueOf(mMax);
        gridTextPaint.getTextBounds(textMax, 0, textMax.length(), textBounds);
        float lineXOffset = textBounds.right;
        float textValueStep = (mMax - mMin) / (float) gridLines;
        for (int i = 0; i <= gridLines; i++) {
//            drawLine(canvas, 0, value, mWidth, value, gridPaint);
            //drawText(canvas, String.valueOf(textValue), 0, value, gridTextPaint);
            gridTextPaint.getTextBounds(text, 0, text.length(), textBounds);
            canvas.drawLine(lineXOffset, value, mWidth, value, gridPaint);
            canvas.drawText(text, lineXOffset - (textBounds.right - textBounds.left) - 4,
                    value + (textBounds.bottom - textBounds.top) / 2f, gridTextPaint);
            value -= step;
            textValue += textValueStep;
            text = String.valueOf(textValue);
        }
    }

    public void setData(PlotData data) {
        this.data = data;
        if (data != null) {
            mMin = data.getMin();
            mMax = data.getMax();

            invalidate();
        }
    }
}
