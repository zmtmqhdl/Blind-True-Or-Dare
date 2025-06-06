package com.example.presentation.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.presentation.component.Icon.Close
import com.example.presentation.theme.ProjectTheme
import com.example.presentation.core.ProjectPreview

object ProjectTextField {
    @Composable
    fun OutlinedTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        textStyle: TextStyle = ProjectTheme.typography.m.regular,
        textCenter: Boolean = false,
        label: String? = null,
        placeholder: String? = null,
        leadingIcon: ImageVector? = null,
        trailIcon: ImageVector? = null,
        alwaysVisibleTrailIcon: Boolean = false,
        onTrailIconClick: (() -> Unit)? = null,
        visualTransformation: VisualTransformation = VisualTransformation.None,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            textStyle = textStyle.copy(
                textAlign = if (textCenter) TextAlign.Center else TextAlign.Start
            ),
            label = { label?.let { Text(it) } },
            placeholder = { placeholder?.let { Text(it) } },
            leadingIcon = leadingIcon?.let { { ProjectIcon(icon = it) } },
            trailingIcon = trailIcon?.let {
                {
                    if (alwaysVisibleTrailIcon) {
                        ProjectIcon(
                            icon = it,
                            onClick = onTrailIconClick
                        )
                    } else {
                        if (value.isNotBlank()) {
                            ProjectIcon(
                                icon = it,
                                onClick = onTrailIconClick
                            )
                        }
                    }

                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions.Default,
            singleLine = true,
            shape = ProjectTheme.shape.textField
        )
    }

}


@ProjectPreview
@Composable
private fun ProjectTextField_Outlined_Primary_Preview() {

    var newValue by remember { mutableStateOf("") }

    ProjectTextField.OutlinedTextField(
        value = "",
        onValueChange = { newValue = it },
        label = "label",
        placeholder = "placeholder",
        leadingIcon = Close,
        trailIcon = Close,

        )
}
