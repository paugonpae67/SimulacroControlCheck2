import { render, screen } from '@testing-library/react';
import Home from './index'
describe("Home page tests",// This title describes the suite of tests:
() =>{
    test("Home page contains titles", // This title describes the test:
    () => {
        render(<Home />);
        
        expect(screen.getByText('Find the best vet for your pet')).toBeInTheDocument();
        expect(screen.getByText('Petclinic')).toBeInTheDocument();        // Debe mostrarse una imagen!
    })
})