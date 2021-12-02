package com.idme.mypurchase.common

import junit.framework.TestCase
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilsTest {
    lateinit var SUB: Utils

    @Before
    fun setUp() {
        SUB = Utils()
    }

    @Test
    fun dateStringToDate_ISODateString1_monthDateString() {
        // Arrange
        val isoDateString = "2021-08-02T13:45:00.000Z"

        // Act
        val dateString = SUB.dateStringToDate(isoDateString)

        // Assert
        MatcherAssert.assertThat(dateString, Is.`is`("Aug 02,2021"))
    }

    @Test
    fun dateStringToDate_ISODateString2_monthDateString() {
        // Arrange
        val isoDateString = "2019-01-12T13:45:00.888Z"

        // Act
        val dateString = SUB.dateStringToDate(isoDateString)

        // Assert
        MatcherAssert.assertThat(dateString, Is.`is`("Jan 12,2019"))
    }

    @Test
    fun dateStringToDate_ISODateString3_monthDateString() {
        // Arrange
        val isoDateString = "1999-12-30T13:45:55.888Z"

        // Act
        val dateString = SUB.dateStringToDate(isoDateString)

        // Assert
        MatcherAssert.assertThat(dateString, Is.`is`("Dec 30,1999"))
    }

    @Test
    fun formatPhoneNumber_phoneNum1_formattedPhoneNum() {
        // Arrange
        val phoneNum = "17025555555"

        // Act
        val formattedPhone = SUB.formatPhoneNumber(phoneNum)

        // Assert
        MatcherAssert.assertThat(formattedPhone, Is.`is`("+1(702)555-5555"))
    }

    @Test
    fun formatPhoneNumber_phoneNum2_formattedPhoneNum() {
        // Arrange
        val phoneNum = "29092752234"

        // Act
        val formattedPhone = SUB.formatPhoneNumber(phoneNum)

        // Assert
        MatcherAssert.assertThat(formattedPhone, Is.`is`("+2(909)275-2234"))
    }
}