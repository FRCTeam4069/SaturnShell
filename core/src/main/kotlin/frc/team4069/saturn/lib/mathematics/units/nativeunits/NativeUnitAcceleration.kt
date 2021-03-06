/*
 * Copyright 2019 Lo-Ellen Robotics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package frc.team4069.saturn.lib.mathematics.units.nativeunits

import frc.team4069.saturn.lib.mathematics.units.*
import frc.team4069.saturn.lib.mathematics.units.derived.Acceleration

typealias NativeUnitAcceleration = Acceleration<NativeUnit>

val Number.STUPer100msPerSecond: SIUnit<NativeUnitAcceleration>
    get() = STUPer100ms / 1.second

val SIUnit<NativeUnitAcceleration>.STUPer100msPerSecond get() = (this * 1.second).STUPer100ms
