package com.mycompany.app

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test


class UnitTest {

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    private val ctx = mockk<Context>(relaxed = true)

    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, sum(40, 2))
    }

    @Test
    fun `GET to root gives 200`() {
        every { ctx.queryParam("username") } returns "Roland"
        UserController.create(ctx) // the handler we're testing
        verify { ctx.status(201) }
    }

    @Test
    fun `POST to create users throws for invalid username`() {
        every { ctx.queryParam("username") } returns null
        assertThrows(BadRequestResponse::class.java) { UserController.create(ctx) } // the handler we're testing
    }
}