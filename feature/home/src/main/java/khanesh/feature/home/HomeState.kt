package khanesh.feature.home

import khanesh.shared.core.model.Promotion

sealed interface HomeState {

    data object Loading : HomeState

    data class Error(val message: String) : HomeState

    data class Success(
        val categories: List<String> = CATEGORIES,
        val promotions: List<Promotion>,
    ) : HomeState
}

val CATEGORIES: List<String> = listOf(
    "رمان",
    "مجموعه داستان",
    "موفقیت",
    "شعر",
    "عاشقانه",
    "فانتزی",
    "کودک",
    "نوجوان",
    "فلسفه",
    "روانشناسی",
    "بیوگرافی",
    "کلاسیک",
    "طنز",
    "تاریخی",
    "کسب و کار",
    "سلامت",
    "جنایی",
    "علم و تکنولوژی",
    "سفرنامه",
    "داستان",
    "تخیلی",
    "هنر",
    "حماسی",
    "اسطوره",
    "داستان کوتاه",
    "اجتماعی",
    "سیاسی",
    "مذهبی",
    "عرفانی",
    "نقد ادبی",
    "غیرداستانی",
    "مهاجرت",
    "نمایشنامه",
    "فیلمنامه",
    "ورزشی",
    "آموزشی",
)
