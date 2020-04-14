package com.pat.driver.ina219

data class Calibration(
    val calValue: Int,
    val currentDivider_mA: Int,
    val powerDivider_mW: Int,
    val config: Int
)

val calibration_32V_2A = Calibration(
    4096,
    10,
    2,
    INA219_CONFIG_BVOLTAGERANGE_32V or
            INA219_CONFIG_GAIN_8_320MV or
            INA219_CONFIG_BADCRES_12BIT or
            INA219_CONFIG_SADCRES_12BIT_1S_532US or
            INA219_CONFIG_MODE_SANDBVOLT_CONTINUOUS
)

val calibration_32V_1A = Calibration(
    10240,
    25,
    1,
    INA219_CONFIG_BVOLTAGERANGE_32V or
            INA219_CONFIG_GAIN_8_320MV or
            INA219_CONFIG_BADCRES_12BIT or
            INA219_CONFIG_SADCRES_12BIT_1S_532US or
            INA219_CONFIG_MODE_SANDBVOLT_CONTINUOUS
)

val calibration_16V_400mA = Calibration(
    8192,
    20,
    1,
    INA219_CONFIG_BVOLTAGERANGE_16V or
            INA219_CONFIG_GAIN_1_40MV or
            INA219_CONFIG_BADCRES_12BIT or
            INA219_CONFIG_SADCRES_12BIT_1S_532US or
            INA219_CONFIG_MODE_SANDBVOLT_CONTINUOUS
)