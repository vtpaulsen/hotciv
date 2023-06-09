/** Demonstration of the fractal map generator.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package demo;

import thirdparty.ThirdPartyFractalGenerator;

public class DemoFractal {

  public static void main(String[] args) {
    ThirdPartyFractalGenerator generator = 
      new ThirdPartyFractalGenerator();
    String line;
    System.out.println("Demonstration of the fractal landscape generator");
    for ( int r = 0; r < 16; r++ ) {
      line = "";
      for ( int c = 0; c < 16; c++ ) {
        line = line + generator.getLandscapeAt(r,c);
      }
      System.out.println( line );
    }
  }
}
