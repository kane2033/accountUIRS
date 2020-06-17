import React from "react";
import {Redirect, Link} from "react-router-dom";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loginClicked: false,
            registerClicked: false,
            adminClicked: false,
            profileClicked: false
        };
    }

    /*    onLoginClick() {
            this.setState({loginClicked: true});
        }

        onRegisterClick() {
            this.setState({registerClicked: true});
        }*/

    //перенаправление по url
    redirectTo(url) {
        return (
            <Redirect push to={{
                pathname: url,
            }} />
        );
    }

    //возможно стоит пользоваться history.push
    render() {
/*        //если одна из кнопок нажата,
        //происходит переадресация на соотв. страницу
        switch (true) {
            case (this.state.loginClicked):
                return this.redirectTo('/login');
            case (this.state.registerClicked):
                return this.redirectTo('/register');
            case (this.state.adminClicked):
                return this.redirectTo('/admin');
            case (this.state.profileClicked):
                return this.redirectTo('/profile');
        }*/

        return (
            <nav id="nav">
                <Link to="/login">Войти</Link>
                <Link to="/profile">Мой профиль</Link>
                {/*<button className="loginButton" onClick={this.setState({loginClicked: true})}>Войти</button>
                <button className="registerButton" onClick={this.setState({registerClicked: true})}>Зарегистрироваться</button>
                <button className="adminButton" onClick={this.setState({adminClicked: true})}>Админ</button>
                <button className="profileButton" onClick={this.setState({profileClicked: true})}>Мой профиль</button>*/}
            </nav>
        );
    }
}

export default HomePage;