document.addEventListener('DOMContentLoaded', () => {
  const imagesSrc = [
    'img/dogs1.jpg',
    'img/dogs2.jpg',
    'img/dogs3.jpg',
    'img/dogs4.jpg',
    'img/dogs6.jpg',
    'img/dogs7.jpg',
    'img/dogs8.jpg',
    'img/dogs9.jpg',
    'img/dogs10.jpg',
    'img/dogs.jpg'
  ];

  const container = document.querySelector('.image-container');
  if (!container) return;

  // Remove imagens estáticas do JSP.
  container.querySelectorAll('img').forEach(img => img.remove());

  // Cria wrapper do carrossel.
  const carousel = document.createElement('div');
  carousel.style.position = 'relative';
  carousel.style.width = '100%';
  carousel.style.height = '100%';
  carousel.style.display = 'flex';
  carousel.style.justifyContent = 'center';
  carousel.style.alignItems = 'center';
  carousel.style.overflow = 'hidden';
  container.appendChild(carousel);

  // Cria imagens.
  const images = imagesSrc.map(src => {
    const img = document.createElement('img');
    img.src = src;
    img.alt = 'Cachorro';
    img.style.position = 'absolute';
    img.style.width = '60%';
    img.style.height = '100%';
    img.style.objectFit = 'cover';
    img.style.borderRadius = '20px';
    img.style.transition = 'all 1s ease';
    img.style.boxShadow = '0 8px 25px rgba(0,0,0,0.5)';
    img.style.opacity = '0';
    carousel.appendChild(img);
    return img;
  });

  let current = 0;
  const total = images.length;

  function updateCarousel() {
    const prev = (current - 1 + total) % total;
    const next = (current + 1) % total;

    images.forEach((img, i) => {
      img.style.opacity = '0';
      img.style.transform = 'translateX(0) scale(0.8)';
      img.style.zIndex = '0';
    });

    // imagem anterior.
    images[prev].style.opacity = '0.5';
    images[prev].style.transform = 'translateX(-150px) scale(0.8)';
    images[prev].style.zIndex = '1';

    // imagem atual (centro).
    images[current].style.opacity = '1';
    images[current].style.transform = 'translateX(0) scale(1.05)';
    images[current].style.zIndex = '3';

    // imagem seguinte.
    images[next].style.opacity = '0.5';
    images[next].style.transform = 'translateX(150px) scale(0.8)';
    images[next].style.zIndex = '1';
  }

  function nextImage() {
    current = (current + 1) % total;
    updateCarousel();
  }

  updateCarousel();
  setInterval(nextImage, 4000);
});
