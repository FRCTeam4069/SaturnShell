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

package frc.team4069.saturn.lib.mathematics.statespace.coeffs

import edu.wpi.first.wpiutil.math.Matrix
import edu.wpi.first.wpiutil.math.Nat
import edu.wpi.first.wpiutil.math.Num

data class StateSpacePlantCoeffs<States: Num, Inputs: Num, Outputs: Num>(val states: Nat<States>, val inputs: Nat<Inputs>, val outputs: Nat<Outputs>,
                                                                         val A: Matrix<States, States>,
                                                                         val B: Matrix<States, Inputs>,
                                                                         val C: Matrix<Outputs, States>,
                                                                         val D: Matrix<Outputs, Inputs>)
