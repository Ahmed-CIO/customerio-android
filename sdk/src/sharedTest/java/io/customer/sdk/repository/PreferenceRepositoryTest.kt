package io.customer.sdk.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.customer.base.extenstions.unixTimeToDate
import io.customer.common_test.BaseTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class PreferenceRepositoryTest : BaseTest() {

    private lateinit var prefRepository: PreferenceRepository

    @Before
    override fun setup() {
        super.setup()

        prefRepository = PreferenceRepositoryImpl(context, cioConfig)
    }

    @Test
    fun httpRequestsPauseEnds_givenNoDateSavedPreviously_expectGetNull() {
        prefRepository.httpRequestsPauseEnds.shouldBeNull()
    }

    @Test
    fun httpRequestsPauseEnds_givenDate_expectSaveDate() {
        val expectedDate = 1650401489L.unixTimeToDate()
        prefRepository.httpRequestsPauseEnds = expectedDate
        val actual = prefRepository.httpRequestsPauseEnds

        expectedDate shouldBeEqualTo actual
    }

    @Test
    fun httpRequestsPauseEnds_givenNull_expectSaveNull() {
        prefRepository.httpRequestsPauseEnds = Date()
        prefRepository.httpRequestsPauseEnds.shouldNotBeNull()

        prefRepository.httpRequestsPauseEnds = null

        prefRepository.httpRequestsPauseEnds.shouldBeNull()
    }
}
