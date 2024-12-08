for $cinema in doc("ex5-t1.xml")//cinema
let $movieCount := count($cinema/movies/title)
order by $movieCount descending
return <CinemaMovieCount>
    <CinemaName>{string($cinema/cinemaname)}</CinemaName>
    <TotalMovies>{ $movieCount }</TotalMovies>
</CinemaMovieCount>

(:output:
    <CinemaMovieCount><CinemaName>
                burgkino
            </CinemaName><TotalMovies>2</TotalMovies></CinemaMovieCount>
    <CinemaMovieCount><CinemaName>
                filmmcasino
            </CinemaName><TotalMovies>1</TotalMovies></CinemaMovieCount>

  description:
    returns the number of movies played in each cinema
:)