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

package frc.team4069.saturn.lib.motor

import frc.team4069.saturn.lib.mathematics.units.Key
import frc.team4069.saturn.lib.mathematics.units.nativeunits.NativeUnitModel

abstract class AbstractSaturnEncoder<T: Key>(val model: NativeUnitModel<T>) : SaturnEncoder<T> {
    override val position get() = model.fromNativeUnitPosition(rawPosition)
    override val velocity get() = model.fromNativeUnitVelocity(rawVelocity)
}