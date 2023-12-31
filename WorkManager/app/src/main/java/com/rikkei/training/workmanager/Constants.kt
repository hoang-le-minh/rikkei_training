@file:JvmName("Constants")

package com.rikkei.training.workmanager


// Notification Channel constants

// Name of Notification Channel for verbose notifications of background work
@JvmField
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"
@JvmField
val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 123

const val RESULT = "result"

const val DELAY_TIME_MILLIS: Long = 3000

const val  NUM_A = "NUM_A"
const val  NUM_B = "NUM_B"
const val SUMMATION = "SUMMATION"
const val SUMMATION_WORK_NAME = "SUMMATION_WORK_NAME"

const val TAG_OUTPUT = "TAG_OUTPUT"

const val IMAGE_URI = "IMAGE_URI"
const val IMAGE_COMPRESSION = "IMAGE_COMPRESSION"
const val RESULT_PATH = "RESULT_PATH"
const val RESULT_FILE_NAME = "RESULT_FILE_NAME"