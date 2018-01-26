package controllers

import play.api.mvc._

trait Secured {

	private def username(request: RequestHeader) = request.session.get("user")

	private def role(request: RequestHeader) = request.session.get("role").getOrElse("NO_ROLE")

	private def onUnauthorized(request: RequestHeader) = Results.Unauthorized

	def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) { user =>
		Action(request => f(user)(request))
	}

	//TODO implement getting role from db instead of form cookie
	def IsAdmin(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
		if (role(request).equals("ROLE_ADMIN")) {
			f(user)(request)
		} else {
			Results.Forbidden
		}
	}

	/**
	 * Check if the connected user is a member of this project.
	 */
	//	def IsMemberOf(project: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
	//		if(Project.isMember(project, user)) {
	//			f(user)(request)
	//		} else {
	//			Results.Forbidden
	//		}
	//	}

}
