package com.fanhl.scoreboardedittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatEditText
import android.text.InputFilter
import android.text.TextPaint
import android.util.AttributeSet

/**
 * EditText with ScoreBoard ui
 *
 * @author fanhl
 */
class ScoreBoardEditText @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatEditText(context, attrs, defStyleAttr) {
    private val maxLength: Int?

    private val textPaint by lazy {
        TextPaint().apply {
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
    }

    private var charBackground: Drawable? = null

    init {
        val theme = context?.theme ?: throw Exception("ScoreBoardEditText.context not Found.")

        textPaint.apply {
            textSize = this@ScoreBoardEditText.textSize
            color = this@ScoreBoardEditText.currentTextColor
        }

        maxLength = filters.firstOrNull { it is InputFilter.LengthFilter }?.let { it as InputFilter.LengthFilter }?.max

        val a = theme.obtainStyledAttributes(attrs, R.styleable.ScoreBoardEditText, defStyleAttr, R.style.Widget_Score_Board_Edit_Text)

        charBackground = a.getDrawable(R.styleable.ScoreBoardEditText_charBackground)

        a.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        canvas ?: return

        val save = canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())

        val width = this.width - paddingLeft - paddingRight
        val height = this.height - paddingTop - paddingBottom

        val textCount = maxLength ?: text.length

        val itemWidth = width / textCount.toFloat()

        for (index in 0 until textCount) {
            val itemX = (index + 0.5f) * itemWidth
            val itemY = height / 2f

            charBackground?.apply {
                setBounds(
                    (itemX - 50).toInt(),
                    (itemY - 50).toInt(),
                    (itemX + 50).toInt(),
                    (itemY + 50).toInt()
                )
                draw(canvas)
            }
            text.getOrNull(index)?.let { char ->
                canvas.drawText(char.toString(), itemX, itemY - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint)
            }
        }

        canvas.restoreToCount(save)
    }
}