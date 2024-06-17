package com.funny.service.support.checksum

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File


class MD5FileExtensionKtTest {

    @Test
    fun testHashFile() {
        assertEquals( hashFile(File("testfile.txt")), "9c891a286397976c0de7d033b7129690")
    }
}