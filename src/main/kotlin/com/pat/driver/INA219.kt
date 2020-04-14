package com.pat.driver

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CFactory
import java.nio.ByteBuffer

class INA219(private val calibration: Calibration = calibration_16V_400mA) {
    private val i2c = I2CFactory.getInstance(I2CBus.BUS_1)
    private val device = i2c.getDevice(INA219_ADDRESS)

    init {
        writeRegister(INA219_REG_CALIBRATION, calibration.calValue.toByteArray())
        writeRegister(INA219_REG_CONFIG, calibration.config.toByteArray())
    }

    fun getCurrent(): Int {
        writeRegister(INA219_REG_CALIBRATION, calibration.calValue.toByteArray())
        return readValue(INA219_REG_CURRENT) / calibration.currentDivider_mA
    }

    fun getBusVoltage(): Double {
        return (readValue(INA219_REG_BUSVOLTAGE) shr 3) * 4 * 0.001
    }

    fun getShuntVoltage(): Double {
        return readValue(INA219_REG_SHUNTVOLTAGE) * 0.01
    }

    private fun readValue(register: Int): Int {
        device.write(register.toByte())
        Thread.sleep(10)
        return ((device.read() shl 8 or device.read()) shl 16) shr 16
    }

    private fun writeRegister(reg: Int, value: ByteArray) {
        device.write(reg, value)
    }
}

private fun Int.toByteArray() = ByteBuffer.allocate(2).putShort(this.toShort()).array()