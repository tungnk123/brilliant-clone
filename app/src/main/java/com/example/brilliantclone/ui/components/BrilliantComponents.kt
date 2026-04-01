package com.example.brilliantclone.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brilliantclone.ui.theme.Border
import com.example.brilliantclone.ui.theme.TextPrimary

// ─── 3D Offset Button ────────────────────────────────────────────────────────
// Two stacked shapes — NOT elevation. The Brilliant signature.

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

    Box(
        modifier = modifier.height(48.dp),
        contentAlignment = Alignment.Center
    ) {
        // Bottom layer: the "depth"
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = offsetDp)
                .background(offsetColor, CircleShape)
        )
        // Top layer: the face — animates down on press
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = currentOffset)
                .background(faceColor, CircleShape)
                .clickable(interactionSource, indication = null) { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = textColor)
        }
    }
}

// ─── Bordered Card ────────────────────────────────────────────────────────────
// Thin 1.5dp border, NO elevation, NO shadow. ONE style throughout the app.

@Composable
fun BrilliantCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateDpAsState(
        targetValue = if (isPressed) (-1).dp else 0.dp,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f),
        label = "card_scale"
    )

    Box(
        modifier = modifier
            .border(1.5.dp, Border, RoundedCornerShape(cornerRadius))
            .background(Color.White, RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius))
            .then(
                if (onClick != null) Modifier.clickable(interactionSource, indication = null) { onClick() }
                else Modifier
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

// ─── Section Label ────────────────────────────────────────────────────────────
// 11sp / SemiBold / UPPERCASE / #888888 — whispers, never screams

@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF888888),
        letterSpacing = 0.8.sp
    )
}

// ─── Tab Pills ────────────────────────────────────────────────────────────────
// Selected: 3D pill. Unselected: bordered pill. NO shared container behind them.

@Composable
fun TabPill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    primaryColor: Color,
    primaryDark: Color
) {
    if (selected) {
        Box(
            modifier = Modifier
                .height(36.dp)
                .wrapContentWidth()
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(y = 2.dp)
                    .background(primaryDark, CircleShape)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(primaryColor, CircleShape)
                    .clickable { onClick() }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }
    } else {
        Box(
            modifier = Modifier
                .height(36.dp)
                .wrapContentWidth()
                .border(1.5.dp, Border, CircleShape)
                .clip(CircleShape)
                .clickable { onClick() }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        }
    }
}

// ─── Category Icon Box ────────────────────────────────────────────────────────
// Colorful rounded-rect background + emoji/icon. Used in tab strip and course cards.

@Composable
fun CategoryIconBox(
    emoji: String,
    bgColor: Color,
    size: Dp = 44.dp,
    cornerRadius: Dp = 12.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(bgColor, RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Text(emoji, fontSize = (size.value * 0.5f).sp)
    }
}
