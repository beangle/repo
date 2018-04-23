/*
 * Beangle, Agile Development Scaffold and Toolkits.
 *
 * Copyright © 2005, The Beangle Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.beangle.repo.artifact

import java.io.File

import org.beangle.commons.collection.Collections
import org.beangle.commons.io.Dirs
import org.junit.runner.RunWith
import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ArtifactDownloaderTest extends FunSpec with Matchers {

  val downloader = ArtifactDownloader(Repo.Remote.AliyunURL, "/tmp/repository")

  val huaweiloader = ArtifactDownloader("https://mirrors.huaweicloud.com/repository/maven/", "/tmp/repository")
  huaweiloader.authorization("anonymous", "devcloud")

  val slf4j_1_7_24 = new Artifact("org.slf4j", "slf4j-api", "1.7.24", None, "jar")
  val slf4j_1_7_25 = new Artifact("org.slf4j", "slf4j-api", "1.7.25", None, "jar")

  val slf4j_1_8_0 = new Artifact("org.slf4j", "slf4j-api", "1.8.0-beta2", None, "jar");

  describe("artifact downloader") {
    it("can download such jars") {
      Dirs.delete(new File("/tmp/repository"))
      val artifacts = Collections.newBuffer[Artifact]
      artifacts += slf4j_1_7_24
      artifacts += slf4j_1_7_25

      artifacts += Artifact("antlr", "antlr", "2.7.7")
      artifacts += Artifact("aopalliance", "aopalliance", "1.0")
      artifacts += Artifact("asm", "asm-commons", "3.3")
      artifacts += Artifact("xml-apis", "xml-apis", "1.4.01")
      artifacts += Artifact("net.sf.json-lib:json-lib:jdk15:2.4")
      downloader.download(artifacts)
    }

    it("can download with password") {
      Dirs.delete(new File("/tmp/repository"))
      huaweiloader.download(List(slf4j_1_8_0))
    }
  }
}
