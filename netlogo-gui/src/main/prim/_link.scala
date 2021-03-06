// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim

import org.nlogo.agent.Link
import org.nlogo.api.{ LogoException}
import org.nlogo.core.Syntax
import org.nlogo.core.Nobody
import org.nlogo.nvm.{ Context, Reporter }

class _link extends Reporter {


  override def report(context: Context): AnyRef = {
    val link =
      world.getLink(argEvalDouble(context, 0), argEvalDouble(context, 1), world.links)
    if (link == null) Nobody else link
  }
}
