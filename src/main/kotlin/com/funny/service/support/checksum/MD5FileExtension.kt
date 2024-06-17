package com.funny.service.support.checksum

import java.io.*
import java.math.BigInteger
import java.security.DigestOutputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


@Throws(IOException::class, NoSuchAlgorithmException::class)
fun hashFile(f: File?): String {
    val md = MessageDigest.getInstance("MD5")

    BufferedInputStream((FileInputStream(f!!))).use { `in` ->
        DigestOutputStream(OutputStream.nullOutputStream(), md).use { out ->
            `in`.transferTo(out)
        }
    }
    val fx = "%0" + (md.digestLength * 2) + "x"
    return String.format(fx, BigInteger(1, md.digest()))
}