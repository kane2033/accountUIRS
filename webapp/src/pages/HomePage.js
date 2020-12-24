import React from "react";
import {Link} from "react-router-dom";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
    }

    //возможно стоит пользоваться history.push
    render() {
        return (
            <nav id="nav">
                <Link to="/login">Войти</Link>
                <Link to="/register">Зарегистрироваться</Link>
                <Link to="/profile">Мой профиль</Link>
                <Link to="/user-movies">Мои фильмы</Link>
                <Link to="movies">Все фильмы</Link>
            </nav>
        );
    }
}

export default HomePage;