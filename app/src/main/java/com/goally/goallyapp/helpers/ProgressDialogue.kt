package com.evaluation.testproject.helpers

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.goally.goallyapp.R

class ProgressDialogue(context: Context) : AlertDialog(context) {

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCanceledOnTouchOutside(false)
    }

    override fun show() {
        super.show()
        setContentView(R.layout.progress_layout)
    }
}