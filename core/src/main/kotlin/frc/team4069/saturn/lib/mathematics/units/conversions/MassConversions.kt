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

package frc.team4069.saturn.lib.mathematics.units.conversions

import frc.team4069.saturn.lib.mathematics.units.Kilogram
import frc.team4069.saturn.lib.mathematics.units.SIConstants
import frc.team4069.saturn.lib.mathematics.units.SIUnit

val SIUnit<Kilogram>.yottagram get() = value / SIConstants.kYottaOffsetKilo
val SIUnit<Kilogram>.zettagram get() = value / SIConstants.kZettaOffsetKilo
val SIUnit<Kilogram>.exagram get() = value / SIConstants.kExaOffsetKilo
val SIUnit<Kilogram>.petagram get() = value / SIConstants.kPetaOffsetKilo
val SIUnit<Kilogram>.teragram get() = value / SIConstants.kTeraOffsetKilo
val SIUnit<Kilogram>.gigagram get() = value / SIConstants.kGigaOffsetKilo
val SIUnit<Kilogram>.megagram get() = value / SIConstants.kMegaOffsetKilo
val SIUnit<Kilogram>.kilogram get() = value
val SIUnit<Kilogram>.hectogram get() = value / SIConstants.kHectoOffsetKilo
val SIUnit<Kilogram>.decagram get() = value / SIConstants.kDecaOffsetKilo
val SIUnit<Kilogram>.gram get() = value / SIConstants.kBaseOffsetKilo
val SIUnit<Kilogram>.decigram get() = value / SIConstants.kDeciOffsetKilo
val SIUnit<Kilogram>.centigram get() = value / SIConstants.kCentiOffsetKilo
val SIUnit<Kilogram>.milligram get() = value / SIConstants.kMilliOffsetKilo
val SIUnit<Kilogram>.microgram get() = value / SIConstants.kMicroOffsetKilo
val SIUnit<Kilogram>.nanogram get() = value / SIConstants.kNanoOffsetKilo
val SIUnit<Kilogram>.picogram get() = value / SIConstants.kPicoOffsetKilo
val SIUnit<Kilogram>.femtogram get() = value / SIConstants.kFemtoOffsetKilo
val SIUnit<Kilogram>.attogram get() = value / SIConstants.kAttoOffsetKilo
val SIUnit<Kilogram>.zeptogram get() = value / SIConstants.kZeptoOffsetKilo
val SIUnit<Kilogram>.yoctogram get() = value / SIConstants.kYoctoOffsetKilo