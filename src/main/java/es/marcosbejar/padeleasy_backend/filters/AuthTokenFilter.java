package es.marcosbejar.padeleasy_backend.filters;

/*@RequiredArgsConstructor
@Log4j2
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userdtl;

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = parseJwt(request);

        if(jwt != null && this.jwtUtils.validateJwtToken(jwt)){
            String username = this.jwtUtils.getUserNameFromToken(jwt);
            UserDetails userDetails = userdtl.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}*/
