package com.example.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.core.core.ProjectPreview
import com.example.core.theme.ProjectTheme

@Composable
fun PrimaryDialog(
    onDismissRequest: (() -> Unit) = {},
    title: String? = null,
    certification: String? = null,
    subText: String? = null,
    text: String? = null,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.4f)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = ProjectTheme.space.space8)
                .background(
                    color = ProjectTheme.color.background,
                    shape = ProjectTheme.shape.dialog
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = ProjectTheme.space.space4,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))
                title?.let {
                    Text(
                        text = it,
                        color = ProjectTheme.color.primary.fontColor,
                        style = ProjectTheme.typography.xl.bold
                    )
                    Spacer(modifier = Modifier.height(ProjectTheme.space.space2))
                }
                certification?.let {
                    Text(
                        text = it,
                        color = ProjectTheme.color.primary.fontColor,
                        style = ProjectTheme.typography.xxl.regular
                    )
                    Spacer(modifier = Modifier.height(ProjectTheme.space.space2))
                }
                subText?.let {
                    Text(
                        text = it,
                        color = ProjectTheme.color.gray600,
                        style = ProjectTheme.typography.s.regular
                    )
                    Spacer(modifier = Modifier.height(ProjectTheme.space.space2))
                }
                text?.let {
                    Text(
                        text = text,
                        color = ProjectTheme.color.black,
                        style = ProjectTheme.typography.m.regular
                    )
                }
                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))
                content()
                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))
            }
        }
    }
}

object ProjectDialog {
    object Single {
        @Composable
        fun SingleArrangement(
            title: String? = null,
            certification: String? = null,
            subText: String? = null,
            text: String,
            buttonText: String,
            onClick: () -> Unit,
            onDismissRequest: (() -> Unit) = {},
        ) {
            PrimaryDialog(
                title = title,
                certification = certification,
                subText = subText,
                text = text,
                onDismissRequest = onDismissRequest
            ) {
                ProjectButton.Primary.Medium(
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
            onClick2: () -> Unit,
            onDismissRequest: (() -> Unit) = {},
        ) {
            PrimaryDialog(
                title = title,
                certification = certification,
                subText = subText,
                text = text,
                onDismissRequest = onDismissRequest
            ) {
                Column {
                    ProjectButton.Primary.Medium(
                        text = buttonText2,
                        onClick = onClick2,
                    )
                    Spacer(modifier = Modifier.height(ProjectTheme.space.space3))
                    ProjectButton.Primary.Medium(
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
            onDismissRequest: (() -> Unit) = {},
        ) {
            PrimaryDialog(
                title = title,
                certification = certification,
                subText = subText,
                text = text,
                onDismissRequest = onDismissRequest
            ) {
                Row {
                    ProjectButton.Primary.Medium(
                        text = buttonText2,
                        onClick = onClick2,
                        modifier = Modifier.Companion.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(ProjectTheme.space.space2))
                    ProjectButton.Primary.Medium(
                        text = buttonText1,
                        onClick = onClick1,
                        modifier = Modifier.Companion.weight(1f)
                    )
                }
            }
        }
    }
}

@ProjectPreview
@Composable
private fun ProjectDialog_Single_SingleArrangement_Preview() {
    ProjectTheme {
        ProjectDialog.Single.SingleArrangement(
            title = "title",
            certification = "certification",
            subText = "subText",
            text = "text",
            buttonText = "button",
            onClick = { },
            onDismissRequest = {}
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectDialog_Double_ColumnArrangement_Preview() {
    ProjectTheme {
        ProjectDialog.Double.ColumnArrangement(
            title = "title",
            certification = "certification",
            subText = "subText",
            text = "text",
            buttonText1 = "button1",
            buttonText2 = "button2",
            onClick1 = { },
            onClick2 = { },
            onDismissRequest = {}

        )
    }
}

@ProjectPreview
@Composable
private fun ProjectDialog_Double_RowArrangement_Preview() {
    ProjectTheme {
        ProjectDialog.Double.RowArrangement(
            title = "title",
            certification = "certification",
            subText = "subText",
            text = "text",
            buttonText1 = "button1",
            buttonText2 = "button2",
            onClick1 = { },
            onClick2 = { },
            onDismissRequest = {}
        )
    }
}