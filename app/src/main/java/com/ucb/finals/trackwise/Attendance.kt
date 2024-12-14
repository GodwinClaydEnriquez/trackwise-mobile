package com.ucb.finals.trackwise

data class Attendance(
    val id: String,
    val name: String,
    val edpCode: String?,
    val subject: String?,
    val departmentOrSection: String,
    val teachingOrInstructor: String,
    val remarks: String,
    val timeIn: String,
    val timeOut: String
)
