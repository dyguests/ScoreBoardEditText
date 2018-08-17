package com.fanhl.scoreboardedittext

import android.content.Context
import android.content.res.ColorStateList
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
class ScoreBoardEditText @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.support.v7.appcompat.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private val maxLength: Int?

    private val textPaint by lazy {
        TextPaint().apply {
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
    }

    private var charBackground: Drawable? = null
    private var charPadding: Int = 0

    init {
        isCursorVisible = false

        textPaint.apply {
            textSize = this@ScoreBoardEditText.textSize
            color = this@ScoreBoardEditText.currentTextColor
            typeface = this@ScoreBoardEditText.typeface
        }

        maxLength = filters.firstOrNull { it is InputFilter.LengthFilter }?.let { it as InputFilter.LengthFilter }?.max

        val theme = context?.theme ?: throw Exception("ScoreBoardEditText.context not Found.")

        val a = theme.obtainStyledAttributes(attrs, R.styleable.ScoreBoardEditText, defStyleAttr, R.style.Widget_Score_Board_Edit_Text)

        charBackground = a.getDrawable(R.styleable.ScoreBoardEditText_charBackground)
        charPadding = a.getDimensionPixelOffset(R.styleable.ScoreBoardEditText_charPadding, resources.getDimensionPixelOffset(R.dimen.sbet_char_padding))

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

        val itemWidth = (width - charPadding * (textCount - 1)) / textCount.toFloat()

        for (index in 0 until textCount) {
            val itemX = 0.5f * itemWidth + index * (itemWidth + charPadding)
            val itemY = height / 2f

            charBackground?.apply {
                setBounds(
                    (itemX - itemWidth / 2).toInt(),
                    (itemY - height / 2).toInt(),
                    (itemX + itemWidth / 2).toInt(),
                    (itemY + height / 2).toInt()
                )
                draw(canvas)
            }
            text.getOrNull(index)?.let { char ->
                canvas.drawText(char.toString(), itemX, itemY - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint)
            }
        }

        canvas.restoreToCount(save)
    }

    override fun setBackground(background: Drawable?) {
//        super.setBackground(background)
    }

    override fun setBackgroundColor(color: Int) {
//        super.setBackgroundColor(color)
    }

    override fun setBackgroundTintList(tint: ColorStateList?) {
//        super.setBackgroundTintList(tint)
    }

    override fun setBackgroundDrawable(background: Drawable?) {
//        super.setBackgroundDrawable(background)
    }

    override fun setBackgroundResource(resId: Int) {
//        super.setBackgroundResource(resId)
    }
}