package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.MyWorldTheme
import com.example.presentation.util.MyWorldPreview

@Composable
fun PrimaryDialog(
    title: String? = null,
    certification: String? = null,
    subText: String? = null,
    text: String,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyWorldTheme.color.gray600),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = MyWorldTheme.space.space8)
                .background(
                    color = MyWorldTheme.color.white,
                    shape = MyWorldTheme.shape.dialog
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = MyWorldTheme.space.space4,
                        end = MyWorldTheme.space.space4
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(MyWorldTheme.space.space4))
                title?.let {
                    Text(
                        text = it,
                        color = MyWorldTheme.color.black,
                        style = MyWorldTheme.typography.xl.bold
                    )
                    Spacer(modifier = Modifier.height(MyWorldTheme.space.space2))
                }
                certification?.let {
                    Text(
                        text = it,
                        color = MyWorldTheme.color.primary.fontColor,
                        style = MyWorldTheme.typography.xxl.regular
                    )
                    Spacer(modifier = Modifier.height(MyWorldTheme.space.space2))
                }
                subText?.let {
                    Text(
                        text = it,
                        color = MyWorldTheme.color.gray600,
                        style = MyWorldTheme.typography.s.regular
                    )
                    Spacer(modifier = Modifier.height(MyWorldTheme.space.space2))
                }
                Text(
                    text = text,
                    color = MyWorldTheme.color.black,
                    style = MyWorldTheme.typography.m.regular
                )
                Spacer(modifier = Modifier.height(MyWorldTheme.space.space4))
                content()
                Spacer(modifier = Modifier.height(MyWorldTheme.space.space4))
            }
        }
    }
}

object MyWorldDialog {
    object Single {
        @Composable
        fun SingleArrangement(
            title: String? = null,
            certification: String? = null,
            subText: String? = null,
            text: String,
            buttonText: String,
            onClick: () -> Unit
        ) {
            PrimaryDialog(
                title = title,
                certification = certification,
                subText = subText,
                text = text,
            ) {
                MyWorldButton.CTA.Medium(
                    text = buttonText,
                    onClick = onClick,
                )
            }
        }
    }

    object Double {
        @Composable
        fun ColumnArrangement(
            title: String? = null,
            certification: String? = null,
            subText: String? = null,
            text: String,
            buttonText1: String,
            buttonText2: String,
            onClick1: () -> Unit,
            onClick2: () -> Unit
        ) {
            PrimaryDialog(
                title = title,
                certification = certification,
                subText = subText,
                text = text,
            ) {
                Column {
                    MyWorldButton.CTA.Medium(
                        text = buttonText2,
                        onClick = onClick2,
                    )
                    Spacer(modifier = Modifier.height(MyWorldTheme.space.space3))
                    MyWorldButton.Primary.Medium(
                        text = buttonText1,
                        onClick = onClick1,
                    )
                }
            }
        }

        @Composable
        fun RowArrangement(
            title: String? = null,
            certification: String? = null,
            subText: String? = null,
            text: String,
            buttonText1: String,
            buttonText2: String,
            onClick1: () -> Unit,
            onClick2: () -> Unit,
        ) {
            PrimaryDialog(
                title = title,
                certification = certification,
                subText = subText,
                text = text,
            ) {
                Row {
                    MyWorldButton.CTA.Medium(
                        text = buttonText2,
                        onClick = onClick2,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(MyWorldTheme.space.space2))
                    MyWorldButton.Primary.Medium(
                        text = buttonText1,
                        onClick = onClick1,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@MyWorldPreview
@Composable
private fun MyWorldDialog_Single_SingleArrangement_Preview() {
    MyWorldTheme {
        MyWorldDialog.Single.SingleArrangement(
            title = "title",
            certification = "certification",
            subText = "subText",
            text = "text",
            buttonText = "button",
            onClick = { }
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldDialog_Double_ColumnArrangement_Preview() {
    MyWorldTheme {
        MyWorldDialog.Double.ColumnArrangement(
            title = "title",
            certification = "certification",
            subText = "subText",
            text = "text",
            buttonText1 = "button1",
            buttonText2 = "button2",
            onClick1 = { },
            onClick2 = { }
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldDialog_Double_RowArrangement_Preview() {
    MyWorldTheme {
        MyWorldDialog.Double.RowArrangement(
            title = "title",
            certification = "certification",
            subText = "subText",
            text = "text",
            buttonText1 = "button1",
            buttonText2 = "button2",
            onClick1 = { },
            onClick2 = { }
        )
    }
}