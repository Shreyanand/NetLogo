// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim.etc

import org.nlogo.api.{ Dump}
import org.nlogo.core.Syntax
import org.nlogo.nvm.{ Command, Context}
import org.nlogo.nvm.RuntimePrimitiveException

class _error extends Command {

  override def perform(context: Context) {
    throw new RuntimePrimitiveException(context, this,
      Dump.logoObject(args(0).report(context)))
  }
}
