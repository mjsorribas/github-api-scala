package codecheck.github
package operations

import exceptions._

import org.scalatest.path.FunSpec
import scala.concurrent.Await

class CollaboratorOpSpec extends FunSpec with api.Constants
{

  describe("addCollaborator"){
    ignore("should add Collaborator User to user Repo"){
      val res = Await.result(api.addCollaborator(user, userRepo, collaboratorUser),TIMEOUT)
      assert(res)
    }
    ignore("should fail for non existent User Repo"){
      val res = Await.result(api.addCollaborator(user, repoInvalid, collaboratorUser).failed,TIMEOUT)
      res match {
        case e: NotFoundException  =>
        case _ => fail
      }
    }
  }
  describe("isCollaborator"){
    ignore("if it is Collaborator"){
      val res = Await.result(api.isCollaborator(user, userRepo, collaboratorUser),TIMEOUT)
      assert(res)
    }
    ignore("if it is not a valid Collaborator"){
      val res1 = Await.result(api.isCollaborator(user, userRepo, otherUserInvalid),TIMEOUT)
      assert(res1 == false)
    }
  }
  describe("listCollaborators"){
    ignore("should return at least one Collaborator"){
      val res = Await.result(api.listCollaborators(user, userRepo),TIMEOUT)
      val c = res.find(_.login == collaboratorUser)
      assert(c.isDefined)
      assert(c.get.id > 0)
      assert(c.get.avatar_url.length > 0)
      assert(c.get.url.length > 0)
      assert(c.get.site_admin == false)
    }
  }
 describe("removeCollaborator"){
    ignore("should remove the Collaborator"){
      var res = Await.result(api.removeCollaborator(user, userRepo, collaboratorUser),TIMEOUT)
      assert(res == true)
    }
  }
  ignore("should fail for non existent User Repo"){
    var res = Await.result(api.removeCollaborator(user, repoInvalid, collaboratorUser).failed,TIMEOUT)
    res match {
      case e: NotFoundException  =>
      case _ => fail
    }
  }
}
