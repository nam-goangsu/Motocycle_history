package pol3436.test.moto_history.utill

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.properties.Delegates

class CustomTextView : View {

    lateinit var mContext :Context
    lateinit var text : String


    private lateinit var paint : Paint
    private val TAG = "CustomTextview : "
    var canvasHeight by Delegates.notNull<Int>()
    var canvasWidth by Delegates.notNull<Int>()
    var maxTextSize : Float = 50.0f


    constructor(mContext: Context?) : super(mContext!!) {
        this.mContext = mContext!!

    }

    constructor(mContext: Context, attrs: AttributeSet) : super(mContext, attrs) {
        this.mContext = mContext!!
        this.canvasWidth = canvasWidth!!
    }

    constructor(mContext: Context, attrs: AttributeSet, defStyleAttr: Int) : super(mContext, attrs, defStyleAttr) {
        this.mContext = mContext!!
        this.canvasWidth = canvasWidth!!
    }
/*    private fun initFont() {
        val tf = FontUtil.instance().fontTypeface
        setTypeface(tf, typeface.style)
    }*/

    private fun initPaint() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.strokeWidth = 1f
    }

    fun setText(text: String?): CustomTextView {
        this.text = text!!
        return this
    }


    fun setMaxTextSize(size: Float): CustomTextView {
        this.maxTextSize = size
        // 최대 사이즈 지정
        return this
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.canvasWidth = width
        this.canvasHeight = height
    }

    override fun onDraw(canvas: Canvas) {
        drawText(canvas)
    }

    private fun drawText(canvas: Canvas) {
        val textSize = getRightSize()
        paint.textSize = sp2px(textSize).toFloat()

        /* int x = (int) (canvasWidth / 2 -paint.measureText(text)/2);
        int y = (int) ((canvasHeight/2) - ((paint.descent()+paint.ascent())/2));*/Log.d(
            TAG,
            "text size2 : $textSize"
        )
        canvas.drawText(text, 10f, 109f, paint)
        // canvas.drawtext(문구 ,시작 x축 , 시작y축 ,
    }

    private fun getRightSize(): Float { // 택스트 사이즈 설정
        paint.textSize = sp2px(maxTextSize.toFloat()).toFloat()
        Log.d(TAG, "max size : $maxTextSize")
        //maxTextSize 최대 크기 값
        paint.textAlign = Paint.Align.LEFT
        val preWidth = paint.measureText(text)
        Log.d(TAG, "preWidth : $preWidth") // 택스트 총 길이
        if (preWidth < canvasWidth) { /// text 길이보다 cavas wtidth가 크면 최대 크기
            return maxTextSize
        }
        Log.d(TAG, "camvaswidhth size : $canvasWidth")
        return maxTextSize * canvasWidth / preWidth // 최대 택스트 크기 * 캔버스 길이 / 글자 총 길이
    }


    private fun sp2px(spValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (spValue * scale + 0.5f).toInt()
    }
    /**
     * 폰트 사이즈 설정에 따라 추가 및 나중에 layout 크기에 따른 글씨 조절 등
     */
}