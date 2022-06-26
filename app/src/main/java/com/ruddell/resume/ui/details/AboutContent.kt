package com.ruddell.resume.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruddell.resume.R
import com.ruddell.resume.models.ItemType
import com.ruddell.resume.ui.shared.ThemedCard
import com.ruddell.resume.ui.shared.TitleView

@Preview
@Composable
fun AboutContent() {
    val selectedItem = remember { mutableStateOf(ItemType.UNSET) }
    LazyColumn {
        item { AboutButton(title = stringResource(id = R.string.third_party_licenses)) {
            selectedItem.value = ItemType.LICENSES
        } }
        item { AboutButton(title = stringResource(id = R.string.app_development)) {
            selectedItem.value = ItemType.APP_DEVELOPMENT
        } }
        item { AboutButton(title = stringResource(id = R.string.contact_info)) {
            selectedItem.value = ItemType.CONTACT_INFO
        } }
        when (selectedItem.value) {
            ItemType.LICENSES -> item { ThirdPartyLicenseView() }
            ItemType.APP_DEVELOPMENT -> item { AppDevelopmentView() }
            ItemType.CONTACT_INFO -> item { ContactInfoView() }
            else -> {}
        }
    }
}

@Composable
private fun ThirdPartyLicenseView() {
    ThemedCard {
        TitleView(stringResource(id = R.string.about_license))
    }
}

@Composable
private fun AppDevelopmentView() {
    ThemedCard {
        TitleView(stringResource(id = R.string.development_description))
    }
}

@Composable
private fun ContactInfoView() {
    ThemedCard {
        Column {
            TitleView(text = stringResource(id = R.string.my_name), style = TextStyle(fontSize = 22.sp))
            TitleView(text = stringResource(id = R.string.position), style = TextStyle(fontSize = 22.sp))
            Spacer(modifier = Modifier.height(8.dp))
            TitleView(text = stringResource(id = R.string.phone_number))
            TitleView(text = stringResource(id = R.string.email))
            TitleView(text = stringResource(id = R.string.city_state))
            Spacer(modifier = Modifier.height(8.dp))
            TitleView(text = stringResource(id = R.string.personal_statement))
        }
    }
}

@Composable
fun AboutButton(title: String, onClick: () -> Unit) {
    ThemedCard(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        TitleView(text = title)
    }
}
