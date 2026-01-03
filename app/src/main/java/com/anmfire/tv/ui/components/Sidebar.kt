package com.anmfire.tv.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral300
import com.anmfire.tv.ui.theme.Neutral800
import com.anmfire.tv.ui.theme.Neutral950

@Composable
fun Sidebar(
    currentDestination: String,
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .background(Neutral950)
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SidebarItem(
            icon = Icons.Default.Home,
            label = "Início",
            isSelected = currentDestination == "home",
            onClick = { onNavigate("home") }
        )
        Spacer(modifier = Modifier.height(32.dp))
        SidebarItem(
            icon = Icons.Default.Search,
            label = "Busca",
            isSelected = currentDestination == "search",
            onClick = { onNavigate("search") }
        )
        Spacer(modifier = Modifier.height(32.dp))
        SidebarItem(
            icon = Icons.Default.Favorite,
            label = "Favoritos",
            isSelected = currentDestination == "favorites",
            onClick = { onNavigate("favorites") }
        )
        Spacer(modifier = Modifier.height(32.dp))
        SidebarItem(
            icon = Icons.Default.List,
            label = "Histórico",
            isSelected = currentDestination == "history",
            onClick = { onNavigate("history") }
        )
    }
}

@Composable
fun SidebarItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    
    val backgroundColor by animateColorAsState(
        if (isFocused) BrandAccent else if (isSelected) Neutral800 else Color.Transparent,
        label = "bgColor"
    )
    
    val iconColor by animateColorAsState(
        if (isFocused) Color.White else if (isSelected) BrandAccent else Neutral300,
        label = "iconColor"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .focusable(interactionSource = interactionSource)
            .padding(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
    }
}
