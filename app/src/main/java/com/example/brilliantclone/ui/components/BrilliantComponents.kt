package com.example.brilliantclone.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brilliantclone.ui.theme.Border
import com.example.brilliantclone.ui.theme.PlusJakartaSansFontFamily
import com.example.brilliantclone.ui.theme.Purple
import com.example.brilliantclone.ui.theme.TextPrimary
import com.example.brilliantclone.ui.theme.TextSecondary

// ─── 3D Offset Button ────────────────────────────────────────────────────────

@Composable
fun OffsetButton(
    text: String,
    onClick: () -> Unit,
    faceColor: Color,
    offsetColor: Color,
    textColor: Color = Color.White,
    offsetDp: Dp = 3.dp,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val currentOffset by animateDpAsState(
        targetValue = if (isPressed) offsetDp else 0.dp,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 600f),
        label = "button_offset"
    )

    Box(modifier = modifier.height(48.dp), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = offsetDp)
                .background(offsetColor, CircleShape)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = currentOffset)
                .background(faceColor, CircleShape)
                .clickable(interactionSource, indication = null) { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontFamily = PlusJakartaSansFontFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = textColor)
        }
    }
}

// ─── Bordered Card ────────────────────────────────────────────────────────────

@Composable
fun BrilliantCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .border(1.dp, Border, RoundedCornerShape(cornerRadius))
            .background(Color.White, RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp), content = content)
    }
}

// ─── Category Tile ────────────────────────────────────────────────────────────
// Icon on top + label below. Selected = purple border + purple indicator dot.
// This is what Brilliant actually uses — NOT pill buttons.

@Composable
fun CategoryTile(
    label: String,
    icon: ImageVector,
    iconTint: Color,
    iconBg: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .border(
                    width = if (selected) 1.5.dp else 1.dp,
                    color = if (selected) Purple else Border,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.White, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier.size(34.dp)
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) Purple else TextPrimary
        )
        Spacer(Modifier.height(3.dp))
        // Purple indicator bar — only shown when selected
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(2.dp)
                .background(
                    color = if (selected) Purple else Color.Transparent,
                    shape = CircleShape
                )
        )
    }
}

// ─── Section Label ────────────────────────────────────────────────────────────

@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextSecondary,
        letterSpacing = 0.8.sp
    )
}
