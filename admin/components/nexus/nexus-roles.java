import groovy.json.JsonSlurper
import org.sonatype.nexus.security.user.UserManager
import org.sonatype.nexus.security.role.NoSuchRoleException
 
// unmarshall the parameters as JSON
parsed_args = new JsonSlurper().parseText(args)
 
// some indirect way to retrieve the service we need
authManager = security.getSecuritySystem().getAuthorizationManager(UserManager.DEFAULT_SOURCE)
 
// Try to locate an existing role to update
def existingRole = null
 
try {
    existingRole = authManager.getRole(parsed_args.id)
} catch (NoSuchRoleException ignored) {
    // could not find role
}
 
// Collection-type cast in groovy, here from String[] to Set&amp;amp;amp;amp;amp;amp;amp;lt;String&amp;amp;amp;amp;amp;amp;amp;gt;
privileges = (parsed_args.privileges == null ? new HashSet() : parsed_args.privileges.toSet())
roles = (parsed_args.roles == null ? new HashSet() : parsed_args.roles.toSet())
 
if (existingRole != null) {
    existingRole.setName(parsed_args.name)
    existingRole.setDescription(parsed_args.description)
    existingRole.setPrivileges(privileges)
    existingRole.setRoles(roles)
    authManager.updateRole(existingRole)
} else {
    // Another collection-type cast, from Set&amp;amp;amp;amp;amp;amp;amp;lt;String&amp;amp;amp;amp;amp;amp;amp;gt; to List&amp;amp;amp;amp;amp;amp;amp;lt;String&amp;amp;amp;amp;amp;amp;amp;gt;
    security.addRole(parsed_args.id, parsed_args.name, parsed_args.description, privileges.toList(), roles.toList())
}
